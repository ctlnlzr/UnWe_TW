package api.Handlers;

import api.JsonModels.AgeJsonModels.AgeCreate;
import api.JsonModels.AgeJsonModels.AgeGetResponse;
import api.JsonModels.commonModels.ValidationResponse;
import api.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.util.List;
import java.util.Map;

import services.AdminService;
import services.AgeService;

import static utils.Utils.splitQuery;

public class AgeHandler extends Handler {

    private final AgeService ageService;
    private final AdminService adminService = new AdminService();

    public AgeHandler(ObjectMapper objectMapper, AgeService ageService) {
        super(objectMapper);
        this.ageService = ageService;
    }

    @Override
    protected void execute(HttpExchange exchange) throws Exception {
        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, DELETE");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Authorization");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(204, -1);
            return;
        }
        byte[] respText;
        Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getRawQuery());
        switch (exchange.getRequestMethod()) {
            case "GET":
                ResponseEntity<AgeGetResponse> entity = doGet(params);
                exchange.getResponseHeaders().putAll(entity.getHeaders());
                exchange.sendResponseHeaders(entity.getStatusCode(), 0);
                respText = super.writeResp(entity.getBody());
                break;
            case "POST":
                if(exchange.getRequestHeaders().containsKey("Authorization") && adminService.existToken(exchange.getRequestHeaders().get("Authorization").get(0))) {
                    ResponseEntity<ValidationResponse> entityCreate = doCreate(exchange.getRequestBody());
                    exchange.getResponseHeaders().putAll(entityCreate.getHeaders());
                    exchange.sendResponseHeaders(entityCreate.getStatusCode(), 0);
                    respText = super.writeResp(entityCreate.getBody());
                } else {
                    exchange.getResponseHeaders().putAll(super.getHeaders("Content-Type", "application/json"));
                    exchange.sendResponseHeaders(401, 0);
                    ValidationResponse validationResponse = new ValidationResponse();
                    validationResponse.setResponse("Not Authorized!");
                    respText = super.writeResp(validationResponse);
                }
                break;
            case "DELETE":
                if (exchange.getRequestHeaders().containsKey("Authorization") && adminService.existToken(exchange.getRequestHeaders().get("Authorization").get(0))) {
                    ResponseEntity<ValidationResponse> entityDelete = doDelete(params);
                    exchange.getResponseHeaders().putAll(entityDelete.getHeaders());
                    exchange.sendResponseHeaders(entityDelete.getStatusCode(), 0);
                    respText = super.writeResp(entityDelete.getBody());
                } else {
                    exchange.getResponseHeaders().putAll(super.getHeaders("Content-Type", "application/json"));
                    exchange.sendResponseHeaders(401, 0);
                    ValidationResponse validationResponse = new ValidationResponse();
                    validationResponse.setResponse("Not Authorized!");
                    respText = super.writeResp(validationResponse);
                }
                break;
            default:
                exchange.getResponseHeaders().putAll(super.getHeaders("Content-Type", "application/json"));
                exchange.sendResponseHeaders(405, 0);
                ValidationResponse validationResponse = new ValidationResponse();
                validationResponse.setResponse("Method not allowed!");
                respText = super.writeResp(validationResponse);
        }
        OutputStream os = exchange.getResponseBody();
        os.write(respText);
        os.close();
    }


    private ResponseEntity<AgeGetResponse> doGet(Map<String, List<String>> params) {
        AgeGetResponse response = new AgeGetResponse();
        if (params.containsKey("monthID") && params.containsKey("county")) {
            response.setGroups(ageService.getByMonthAndCounty(Integer.parseInt(params.get("monthID").get(0)), params.get("county").get(0)));
        } else if (params.containsKey("monthID")) {
            response.setGroups(ageService.getByMonth(Integer.parseInt(params.get("monthID").get(0))));
        } else if (params.containsKey("county")) {
            response.setGroups(ageService.getByCounty(params.get("county").get(0)));
        } else if (params.isEmpty()) {
            response.setGroups(ageService.getAll());
        } else response.setGroups(null);

        if (response.getGroups().isEmpty()) {
            return new ResponseEntity<>(null, super.getHeaders("Content-Type", "application/json"), 404);
        }
        return new ResponseEntity<>(response, super.getHeaders("Content-Type", "application/json"), 200);
    }

    private ResponseEntity<ValidationResponse> doDelete(Map<String, List<String>> params) {
        ValidationResponse response = new ValidationResponse();
        if (!params.containsKey("monthID")) {
            response.setResponse("Data not found!");
            return new ResponseEntity<>(response, super.getHeaders("Content-Type", "application/json"), 404);
        }
        if (ageService.deleteAge(Integer.parseInt(params.get("monthID").get(0))))
            response.setResponse("Data was deleted!");
        else response.setResponse("There is no data to delete!");
        return new ResponseEntity<>(response, super.getHeaders("Content-Type", "application/json"), 200);
    }

    private ResponseEntity<ValidationResponse> doCreate(InputStream is) {
        ValidationResponse response = new ValidationResponse();
        AgeCreate request = super.readResp(is, AgeCreate.class);
        if (ageService.saveAge(request)) response.setResponse("The data was added!");
        else response.setResponse("The data wasn't added!");
        return new ResponseEntity<>(response, super.getHeaders("Content-Type", "application/json"), 200);
    }
}
