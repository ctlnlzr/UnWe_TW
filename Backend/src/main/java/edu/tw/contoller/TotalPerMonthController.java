package edu.tw.contoller;

import edu.tw.database.entity.TotalPerMonth;
import edu.tw.services.TotalPerMonthService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/totalPerMonth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TotalPerMonthController {
    TotalPerMonthService service;


    @GET
    @Path("/getByMonth/{month}")
    public List<TotalPerMonth> getByMonth(@PathParam("month") int month) {
        return service.getByMonth(month);
    }

    @GET
    @Path("/getByCounty/{county}")
    public List<TotalPerMonth> getByCounty(@PathParam("county") String county) {
        return service.getByCounty(county);
    }

    @GET
    @Path("/{month}/{county}")
    public List<TotalPerMonth> getByMonthAndCounty(@PathParam("month") int month, @PathParam("county") String county) {
        return service.getByMonthAndCounty(month, county);
    }

    @GET
    public List<TotalPerMonth> getAll() {
        return service.getAll();
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
