/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lasalle.services.controller.ControllerAuthentification;
import org.lasalle.services.model.User;

/**
 *
 * @author Claudio
 */
@Path("auth")
public class RestAuthentification {
    
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login (@FormParam("username") String username,
                            @FormParam("password") String password){
        String out = "";
        try {
            ControllerAuthentification controller = new ControllerAuthentification();
            User user = controller.authentificateUser(username, password);
            out = """
                  {
                      "id": %s,
                      "name": %s,
                      "username": %s,
                      "bio": %s
                  }
                  """;
            out = String.format(out, user.getId(), user.getName(), user.getUsername(), user.getBio());
            
            
        } catch (Exception e){
            out = """
                  {"exception" = %s}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.FORBIDDEN).entity(out).build();
            
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
