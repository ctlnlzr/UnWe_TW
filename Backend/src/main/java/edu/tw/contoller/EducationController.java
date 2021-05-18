package edu.tw.contoller;

import edu.tw.database.entity.Education;
import edu.tw.services.EducationService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/education")
public class EducationController {
    EducationService service;
    @POST
    @Path("/create")
    public Response createEducation(Education education) {
        if(service.saveEducation(education)){
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
    @DELETE
    @Path("/delete/{month}/{county}")
    public Response deleteEducation(@PathParam("month") int month,@PathParam("county") String county){

        return Response.ok().build();

    }

}
