package edu.tw.contoller;

import edu.tw.database.entity.Education;
import edu.tw.database.entity.TotalPerMonth;
import edu.tw.services.EducationService;
import edu.tw.services.TotalPerMonthService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
@Path("/TotalPerMonth")
public class TotalPerMonthController {
    TotalPerMonthService service;
    @POST
    @Path("/create")
    public Response createTotalPerMonth(TotalPerMonth totalPerMonth) {
        if(service.saveTotalPerMonth(totalPerMonth)){
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
    @DELETE
    @Path("/delete/{month}/{county}")
    public Response deleteTotalPerMonth(@PathParam("month") int month,@PathParam("county") String county){

        return Response.ok().build();

    }
}
