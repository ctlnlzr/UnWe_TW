package edu.tw.contoller;

import edu.tw.database.entity.Education;
import edu.tw.services.EducationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/education")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EducationController {
    EducationService service;


    @GET
    @Path("/getByMonth/{month}")
    public List<Education> getByMonth(@PathParam("month") int month) {
        return service.getByMonth(month);
    }

    @GET
    @Path("/getByCounty/{county}")
    public List<Education> getByCounty(@PathParam("county") String county) {
        return service.getByCounty(county);
    }

    @GET
    @Path("/{month}/{county}")
    public List<Education> getByMonthAndCounty(@PathParam("month") int month, @PathParam("county") String county) {
        return service.getByMonthAndCounty(month, county);
    }

    @GET
    public List<Education> getAll() {
        return service.getAll();
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
