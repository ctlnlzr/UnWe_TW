package api.Handlers;


import api.JsonModels.commonModels.ValidationResponse;
import api.ResponseEntity;
import api.JsonModels.adminJsonModels.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import database.entity.Admin;
import services.AdminService;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.List;

import static utils.Utils.splitQuery;

public class AdminHandler extends Handler{

    private final AdminService adminService;

    public AdminHandler(ObjectMapper objectMapper, AdminService adminService) {
        super(objectMapper);
        this.adminService = adminService;
    }

    private ResponseEntity<ValidationResponse> doLogin(InputStream is){
        LoginRequest loginRequest = super.readResp(is, LoginRequest.class);
        Admin admin = new Admin();
        admin.setPassword(loginRequest.getPassword());
        admin.setUsername(loginRequest.getUsername());
        ValidationResponse response = new ValidationResponse();
        if (adminService.find(admin)){
            int token = (int) (Math.random() * 1_000_000);
            response.setResponse(String.valueOf(token));
            admin.setToken(String.valueOf(token));
            adminService.addToken(admin);
        }else{
            response.setResponse("Bad credentials!");
        }
        return new ResponseEntity<>(response,super.getHeaders("Content-Type","application/json"), 200);
    }

    private ResponseEntity<ValidationResponse> doLogout(String authorization) {
        ValidationResponse response = new ValidationResponse();
        adminService.deleteToken(authorization);
        response.setResponse("You've been logged out!");
        return new ResponseEntity<>(response, super.getHeaders("Content-Type","application/json"), 200);
     }

     private ResponseEntity<ValidationResponse> doAddNewAdmin(InputStream is){
        ValidationResponse response = new ValidationResponse();
        LoginRequest loginRequest = super.readResp(is, LoginRequest.class);
        Admin admin = new Admin();
        admin.setPassword(loginRequest.getPassword());
        admin.setUsername(loginRequest.getUsername());
        if(adminService.saveAdmin(admin)) response.setResponse("You added a new admin!");
        else response.setResponse("The admin already exists!");

        return new ResponseEntity<>(response,super.getHeaders("Content-Type","application/json"), 200);
    }
    @Override
    protected void execute(HttpExchange exchange) throws Exception {
        byte[] respText;
        if("POST".equals(exchange.getRequestMethod())){
            if(exchange.getRequestHeaders().get("Authorization") == null){
                ResponseEntity<ValidationResponse> entity = doLogin(exchange.getRequestBody());
            exchange.getResponseHeaders().putAll(entity.getHeaders());
            exchange.sendResponseHeaders(entity.getStatusCode(), 0);
            respText = super.writeResp(entity.getBody());}
            else{
                if(adminService.existToken(exchange.getRequestHeaders().get("Authorization").get(0))) {
                    Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getRawQuery());
                    if(params.containsKey("logout")){
                       ResponseEntity<ValidationResponse> entity = doLogout(exchange.getRequestHeaders().get("Authorization").get(0));
                       exchange.getResponseHeaders().putAll(entity.getHeaders());
                       exchange.sendResponseHeaders(entity.getStatusCode(),0);
                      respText=super.writeResp(entity.getBody());
                    }else{
                        ResponseEntity<ValidationResponse> entity = doAddNewAdmin(exchange.getRequestBody());
                        exchange.getResponseHeaders().putAll(entity.getHeaders());
                        exchange.sendResponseHeaders(entity.getStatusCode(), 0);
                        respText = super.writeResp(entity.getBody());
                    }
                }else{
                    exchange.getResponseHeaders().putAll(getHeaders("Content-Type","application/json"));
                    exchange.sendResponseHeaders(401,-1);
                    respText = "No Authorization".getBytes();
                }
            }
        } else{
            exchange.sendResponseHeaders(405,-1);
            respText = "Not allowed".getBytes();
        }
        OutputStream os = exchange.getResponseBody();
        os.write(respText);
        os.close();
    }


}
