package edu.tw.controller;

import edu.tw.services.PageService;
import org.codehaus.jettison.json.JSONException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/page")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PageController {
    PageService pageService=new PageService();

    @GET
    @Path("/criteria/{criteria1}/{criteria2}/{criteria3}")
    public Object criteria(@PathParam("criteria1")int criteria1,
                           @PathParam("criteria2") String criteria2,
                           @PathParam("criteria3")String criteria3) throws JSONException {
        return (pageService.criteriaService(criteria1, criteria2, criteria3) );
    }
}
