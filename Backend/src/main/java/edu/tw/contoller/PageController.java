package edu.tw.contoller;

import edu.tw.services.PageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1/page")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PageController {
    PageService pageService=new PageService();

    @GET
    @Path("/criteria/{criteria1}/{criteria2}/{criteria3}/{criteria4}/{criteria5}")
    public Response criteria(@PathParam("criteria1")String criteria1,
                             @PathParam("criteria2") String criteria2,
                             @PathParam("criteria3")String criteria3,
                             @PathParam("criteria4")String criteria4,
                             @PathParam("criteria5")String criteria5 ){
        pageService.criteriaService(criteria1, criteria2, criteria3, criteria4, criteria5);
       return Response.ok().build();
    }
    @GET
    @Path("/csv/{criteria1}/{criteria2}/{criteria3}/{criteria4}/{criteria5}")
    public Response csv(@PathParam("criteria1")String criteria1,
                        @PathParam("criteria2") String criteria2,
                        @PathParam("criteria3")String criteria3,
                        @PathParam("criteria4")String criteria4,
                        @PathParam("criteria5")String criteria5){

        pageService.downloadCsv(criteria1, criteria2, criteria3, criteria4, criteria5);
       return Response.ok().build();
    }
    @GET
    @Path("/pdf/{criteria1}/{criteria2}/{criteria3}/{criteria4}/{criteria5}")
    public Response pdf(@PathParam("criteria1")String criteria1,
                        @PathParam("criteria2") String criteria2,
                        @PathParam("criteria3")String criteria3,
                        @PathParam("criteria4")String criteria4,
                        @PathParam("criteria5")String criteria5){
        pageService.downloadPdf(criteria1, criteria2, criteria3, criteria4, criteria5);
        return Response.ok().build();
    }
    @GET
    @Path("/svg/{criteria1}/{criteria2}/{criteria3}/{criteria4}/{criteria5}")
    public Response svg(@PathParam("criteria1")String criteria1,
                        @PathParam("criteria2") String criteria2,
                        @PathParam("criteria3")String criteria3,
                        @PathParam("criteria4")String criteria4,
                        @PathParam("criteria5")String criteria5){
        pageService.downloadSvg(criteria1, criteria2, criteria3, criteria4, criteria5);
        return Response.ok().build();
    }
}
