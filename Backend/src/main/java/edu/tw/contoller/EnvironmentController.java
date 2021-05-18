package edu.tw.contoller;

import edu.tw.database.entity.Education;
import edu.tw.database.entity.Environment;
import edu.tw.services.EducationService;
import edu.tw.services.EnvironmentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public class EnvironmentController {
    EnvironmentService service;
    @POST
    @Path("/create")
    public Response createEnvironment(Environment environment) {
        if(service.saveEnvironment(environment)){
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
    @DELETE
    @Path("/delete/{month}/{county}")
    public Response deleteEnvironment(@PathParam("month") int month, @PathParam("county") String county){

        return Response.ok().build();

    }
}
