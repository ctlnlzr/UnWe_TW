package api.Handlers;

import api.JsonModels.EnvironmentJsonModels.EnvironmentCreate;
import api.JsonModels.EnvironmentJsonModels.EnvironmentResponse;
import api.JsonModels.commonModels.ValidationResponse;
import api.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import services.AdminService;
import services.EnvironmentService;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import static utils.Utils.splitQuery;

public class EnvironmentHandler extends Handler {

    private final EnvironmentService environmentService;
    private final AdminService adminService = new AdminService();

    public EnvironmentHandler(ObjectMapper objectMapper, EnvironmentService environmentService) {
        super(objectMapper);
        this.environmentService = environmentService;
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
                ResponseEntity<EnvironmentResponse> entity = doGet(params);
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
                    break;
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

    private ResponseEntity<EnvironmentResponse> doGet(Map<String, List<String>> params) {
        EnvironmentResponse response = new EnvironmentResponse();
        if (params.containsKey("monthID") && params.containsKey("county")) {
            response.setGroups(environmentService.getByMonthAndCounty(Integer.parseInt(params.get("monthID").get(0)), params.get("county").get(0)));
        } else if (params.containsKey("monthID")) {
            response.setGroups(environmentService.getByMonth(Integer.parseInt(params.get("monthID").get(0))));
        } else if (params.containsKey("county")) {
            response.setGroups(environmentService.getByCounty(params.get("county").get(0)));
        } else if (params.isEmpty()) {
            response.setGroups(environmentService.getAll());
        } else response.setGroups(null);

        if (response.getGroups().isEmpty()) {

            return new ResponseEntity<>(null, 404);
        }

        return new ResponseEntity<>(response,200);
    }

    private ResponseEntity<ValidationResponse> doDelete(Map<String, List<String>> params) {
        ValidationResponse response = new ValidationResponse();
        if (!params.containsKey("monthID")) {
            response.setResponse("Data not found!");
            return new ResponseEntity<>(response, 404);
        }
        if (environmentService.deleteEnvironment(Integer.parseInt(params.get("monthID").get(0))))
            response.setResponse("Data was deleted!");
        else response.setResponse("There is no data to delete!");
        return new ResponseEntity<>(response, 200);
    }

    private ResponseEntity<ValidationResponse> doCreate(InputStream is) {
        ValidationResponse response = new ValidationResponse();
        EnvironmentCreate request = super.readResp(is, EnvironmentCreate.class);
        if (environmentService.saveEnvironment(request)) response.setResponse("The data was added!");
        else response.setResponse("The data wasn't added!");
        return new ResponseEntity<>(response, 200);
    }
}
