package edu.tw.controller;

import com.google.gson.Gson;
import edu.tw.database.entity.Education;
import edu.tw.services.EducationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/education")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EducationController {
    EducationService service=new EducationService();


    @GET
    @Path("/getByMonth/{month}")
    public String  getByMonth(@PathParam("month") int month) {
         return new Gson().toJson(service.getAll() );
    }

    @GET
    @Path("/getByCounty/{county}")
    public String getByCounty(@PathParam("county") String county) {
        return new Gson().toJson(service.getAll() );
    }

    @GET
    @Path("/{month}/{county}")
    public String getByMonthAndCounty(@PathParam("month") int month, @PathParam("county") String county) {
        return new Gson().toJson(service.getAll() );
    }

    @GET
    public String getAll() {
        return new Gson().toJson(service.getAll() );
    }

    @POST
    @Path("/create")
    public Response createEducation(Education education) {
        if (service.saveEducation(education)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/update")
    public Response updateEducation(Education education) {
        if (service.updateEducation(education)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/delete/{month}/{county}")
    public Response deleteEducation(@PathParam("month") int month, @PathParam("county") String county) {

        if (service.deleteEducation(month, county)) {
            return Response.ok().build();
        }
        return Response.noContent().build();

    }

}
