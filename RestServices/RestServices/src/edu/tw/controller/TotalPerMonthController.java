package edu.tw.controller;

import com.google.gson.Gson;
import edu.tw.database.entity.TotalPerMonth;
import edu.tw.services.TotalPerMonthService;
import org.codehaus.jettison.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/totalPerMonth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TotalPerMonthController {
    TotalPerMonthService service = new TotalPerMonthService();


    @GET
    @Path("/getByMonth/{month}")
    public String  getByMonth(@PathParam("month") int month) {
        return new Gson().toJson(service.getByMonth(month));
    }

    @GET
    @Path("/getByCounty/{county}")
    public String getByCounty(@PathParam("county") String county) {
        return new Gson().toJson(service.getByCounty(county));
    }

    @GET
    @Path("/{month}/{county}")
    public String getByMonthAndCounty(@PathParam("month") int month, @PathParam("county") String county) {
        return new Gson().toJson(service.getByMonthAndCounty(month, county));
    }

    @GET
    public String getAll() {
        return new Gson().toJson(service.getAll());
    }

    @POST
    @Path("/create")
    public Response createTotalPerMonth(TotalPerMonth totalPerMonth) {
        if (service.saveTotalPerMonth(totalPerMonth)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/update")
    public Response updateTotalPerMonth(TotalPerMonth totalPerMonth) {
        if (service.updateTotalPerMonth(totalPerMonth)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/delete/{month}/{county}")
    public Response deleteTotalPerMonth(@PathParam("month") int month, @PathParam("county") String county) {

        if (service.deleteTotalPerMonth(month, county)) {
            return Response.ok().build();
        }
        return Response.noContent().build();

    }
}
