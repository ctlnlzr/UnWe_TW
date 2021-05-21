package edu.tw.controller;

import com.google.gson.Gson;
import edu.tw.services.PageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/page")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PageController {
    PageService pageService=new PageService();

    @GET
    @Path("/criteria/{criteria1}/{criteria2}/{criteria3}/{criteria4}/{criteria5}")
    public String criteria(@PathParam("criteria1")int criteria1,
                            @PathParam("criteria2") String criteria2,
                            @PathParam("criteria3")String criteria3,
                            @PathParam("criteria4")String criteria4,
                            @PathParam("criteria5")String criteria5 ){
        return new Gson().toJson(pageService.criteriaService(criteria1, criteria2, criteria3, criteria4, criteria5) );
    }
}
