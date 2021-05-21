package edu.tw.controller;

import com.google.gson.Gson;
import edu.tw.database.entity.Environment;
import edu.tw.services.EnvironmentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/environment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnvironmentController {
    EnvironmentService service=new EnvironmentService();

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
        return new Gson().toJson(service.getByMonthAndCounty(month, county) );
    }

    @GET
    public String getAll() {
        return new Gson().toJson(service.getAll() );
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
