package edu.tw.contoller;

import edu.tw.database.entity.Age;
import edu.tw.database.entity.Education;
import edu.tw.services.AgeService;
import edu.tw.services.EducationService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public class AgeController {
    AgeService service;
    @POST
    @Path("/create")
    public Response createEducation(Age age) {
        if(service.saveAge(age)){
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
    @DELETE
    @Path("/delete/{month}/{county}")
    public Response deleteEducation(@PathParam("month") int month, @PathParam("county") String county){

        return Response.ok().build();

    }
}
