package api.Handlers;

import api.JsonModels.EducationJsonModels.EducationCreate;
import api.JsonModels.EducationJsonModels.EducationGetResponse;
import api.JsonModels.commonModels.ValidationResponse;
import api.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import services.AdminService;
import services.EducationService;

import java.io.*;
import java.util.List;
import java.util.Map;

import static utils.Utils.splitQuery;

public class EducationHandler extends Handler {

    private final EducationService educationService;
    private final AdminService adminService = new AdminService();

    public EducationHandler(ObjectMapper objectMapper, EducationService educationService) {
        super(objectMapper);
        this.educationService = educationService;
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
                ResponseEntity<EducationGetResponse> entity = doGet(params);
                exchange.getResponseHeaders().putAll(super.getHeaders());
                exchange.sendResponseHeaders(entity.getStatusCode(), 0);
                respText = super.writeResp(entity.getBody());
                break;
            case "POST":
                if (exchange.getRequestHeaders().containsKey("Authorization") && adminService.existToken(exchange.getRequestHeaders().get("Authorization").get(0))) {
                    ResponseEntity<ValidationResponse> entityCreate = doCreate(exchange.getRequestBody());
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                    exchange.sendResponseHeaders(entityCreate.getStatusCode(), 0);
                    respText = super.writeResp(entityCreate.getBody());
                } else {
                    exchange.sendResponseHeaders(401, 0);
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                    ValidationResponse validationResponse = new ValidationResponse();
                    validationResponse.setResponse("Not Authorized!");
                    respText = super.writeResp(validationResponse);
                 }
                break;
            case "DELETE":
                if (exchange.getRequestHeaders().containsKey("Authorization") && adminService.existToken(exchange.getRequestHeaders().get("Authorization").get(0))) {
                    ResponseEntity<ValidationResponse> entityDelete = doDelete(params);
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                    exchange.sendResponseHeaders(entityDelete.getStatusCode(), 0);
                    respText = super.writeResp(entityDelete.getBody());
                } else {
                    exchange.sendResponseHeaders(401, 0);
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                    ValidationResponse validationResponse = new ValidationResponse();
                    validationResponse.setResponse("Not Authorized!");
                    respText = super.writeResp(validationResponse);
                }
                break;
            default:
                exchange.sendResponseHeaders(405, 0);
                exchange.getResponseHeaders().putAll(super.getHeaders());
                ValidationResponse validationResponse = new ValidationResponse();
                validationResponse.setResponse("Method not allowed!");
                respText = super.writeResp(validationResponse);
        }
        OutputStream os = exchange.getResponseBody();
        os.write(respText);
        os.close();
    }

    private ResponseEntity<EducationGetResponse> doGet(Map<String, List<String>> params) {
        EducationGetResponse response = new EducationGetResponse();
        if (params.containsKey("monthID") && params.containsKey("county")) {
            response.setGroups(educationService.getByMonthAndCounty(Integer.parseInt(params.get("monthID").get(0)), params.get("county").get(0)));
        } else if (params.containsKey("monthID")) {
            response.setGroups(educationService.getByMonth(Integer.parseInt(params.get("monthID").get(0))));
        } else if (params.containsKey("county")) {
            response.setGroups(educationService.getByCounty(params.get("county").get(0)));
        } else if (params.isEmpty()) {
            response.setGroups(educationService.getAll());
        } else response.setGroups(null);

        if (response.getGroups().isEmpty()) {
            return new ResponseEntity<>(null, 404);
        }
        return new ResponseEntity<>(response, 200);
    }

    ResponseEntity<ValidationResponse> doCreate(InputStream is) {
        ValidationResponse response = new ValidationResponse();
        EducationCreate request = super.readResp(is, EducationCreate.class);
        if (educationService.saveEducation(request)) response.setResponse("Data wad added");
        else response.setResponse("Data wasn`t added");
        return new ResponseEntity<>(response, 200);
    }


    ResponseEntity<ValidationResponse> doDelete(Map<String, List<String>> params) {
        ValidationResponse validationResponse = new ValidationResponse();
        if (!params.containsKey("monthID")) {
            validationResponse.setResponse("Data not found!");
            return new ResponseEntity<>(validationResponse, 404);
        }
        if (educationService.deleteEducation(Integer.parseInt(params.get("monthID").get(0))))
            validationResponse.setResponse("Data was deleted!");
        else
            validationResponse.setResponse("There is no data to delete!");

        return new ResponseEntity<>(validationResponse, 200);
    }
}


