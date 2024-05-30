/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lasalle.services.controller.ControllerFollowers;

/**
 *
 * @author Claudio
 */
@Path("followers")
public class RestFollowers {
    @Path("followsCount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response followsCount (@QueryParam("username") String username){
        String out = "";
        try {
            System.out.println(username);
            ControllerFollowers controller = new ControllerFollowers();
            final int count = controller.getFollowsCount(username);
            out = """
                  {
                      "followsCount": %s
                  }
                  """;
            out = String.format(out, count);
            
            
        } catch (Exception e){
            out = """
                  {"exception" = %s}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            
         } catch (Error e){
             out = """
                  {"error" = %s}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
         }
        return Response.ok(out).build();

    }
}
