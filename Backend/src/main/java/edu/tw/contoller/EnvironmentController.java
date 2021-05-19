package edu.tw.contoller;

import edu.tw.database.entity.Environment;
import edu.tw.services.EnvironmentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/environment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnvironmentController {
    EnvironmentService service;

    @GET
    @Path("/getByMonth/{month}")
    public List<Environment> getByMonth(@PathParam("month") int month) {
        return service.getByMonth(month);
    }

    @GET
    @Path("/getByCounty/{county}")
    public List<Environment> getByCounty(@PathParam("county") String county) {
        return service.getByCounty(county);
    }

    @GET
    @Path("/{month}/{county}")
    public List<Environment> getByMonthAndCounty(@PathParam("month") int month, @PathParam("county") String county) {
        return service.getByMonthAndCounty(month, county);
    }

    @GET
    public List<Environment> getAll() {
        return service.getAll();
    }

    @POST
    @Path("/create")
    public Response createEnvironment(Environment environment) {
        if (service.saveEnvironment(environment)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/update")
    public Response updateEnvironment(Environment environment) {
        if (service.updateEnvironment(environment)) {
            return Response.ok().build();
        }
        return Response.noContent().build();

    }

    @DELETE
    @Path("/delete/{month}/{county}")
    public Response deleteEnvironment(@PathParam("month") int month, @PathParam("county") String county) {

        if (service.deleteEnvironment(month, county)) {
            return Response.ok().build();
        }
        return Response.noContent().build();

    }
}
