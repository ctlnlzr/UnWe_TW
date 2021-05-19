package edu.tw.contoller;

import edu.tw.database.entity.Age;
import edu.tw.services.AgeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/age")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgeController {
    AgeService service;

    @GET
    public List<Age> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/getByMonth/{month}")
    public List<Age> getByMonth(@PathParam("month") int month) {
        return service.getByMonth(month);
    }

    @GET
    @Path("/getByCounty/{county}")
    public List<Age> getByCounty(@PathParam("county") String county) {
        return service.getByCounty(county);
    }

    @GET
    @Path("/{month}/{county}")
    public List<Age> getByMonthAndCounty(@PathParam("month") int month, @PathParam("county") String county) {
        return service.getByMonthAndCounty(month, county);
    }

    @POST
    @Path("/create")
    public Response createEducation(Age age) {
        if (service.saveAge(age)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/update")
    public Response updateAge(Age age) {
        if (service.updateAge(age)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/delete/{month}/{county}")
    public Response deleteEducation(@PathParam("month") int month, @PathParam("county") String county) {
        if (service.deleteAge(month, county)) {
            return Response.ok().build();
        }
        return Response.noContent().build();

    }
}
