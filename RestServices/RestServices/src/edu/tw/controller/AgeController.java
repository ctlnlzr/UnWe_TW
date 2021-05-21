package edu.tw.controller;

import com.google.gson.Gson;
import edu.tw.database.entity.Age;
import edu.tw.services.AgeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/age")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgeController {
    AgeService service=new AgeService();

    @GET
    public String getAll() {
       String  json = new Gson().toJson(service.getAll() );
        return json;
    }

    @GET
    @Path("/getByMonth/{month}")
    public String getByMonth(@PathParam("month") int month) {
        return new Gson().toJson(service.getByMonth(month));
    }

    @GET
    @Path("/getByCounty/{county}")
    public String getByCounty(@PathParam("county") String county) {
        return new Gson().toJson(service.getByCounty(county) );
    }

    @GET
    @Path("/{month}/{county}")
    public String getByMonthAndCounty(@PathParam("month") int month, @PathParam("county") String county) {
      return   new Gson().toJson(service.getByMonthAndCounty(month, county) );
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
