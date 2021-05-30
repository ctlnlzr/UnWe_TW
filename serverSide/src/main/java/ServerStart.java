import api.Handlers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import services.*;

import java.io.IOException;
import java.net.InetSocketAddress;


public class ServerStart {


    public static void main(String[] args) throws IOException {
        int serverPort = 8090;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        //initialize handlers
        AdminHandler adminHandler = new AdminHandler(new ObjectMapper(), new AdminService());
        AgeHandler ageHandler = new AgeHandler(new ObjectMapper(), new AgeService());
        EducationHandler educationHandler = new EducationHandler(new ObjectMapper(), new EducationService());
        EnvironmentHandler environmentHandler = new EnvironmentHandler(new ObjectMapper(), new EnvironmentService());
        PageHandler pageHandler = new PageHandler(new ObjectMapper());
        TotalPerMonthHandler totalHandler = new TotalPerMonthHandler(new ObjectMapper(), new TotalPerMonthService());

        server.createContext("/api/v1/admin", adminHandler::handle);
        server.createContext("/api/v1/age", ageHandler::handle);
        server.createContext("/api/v1/education",educationHandler::handle);
        server.createContext("/api/v1/environment", environmentHandler::handle);
        server.createContext("/api/v1/total", totalHandler::handle);
        server.createContext("/api/v1/criterion", pageHandler::handle);

        server.setExecutor(null);
        server.start();
    }
}
