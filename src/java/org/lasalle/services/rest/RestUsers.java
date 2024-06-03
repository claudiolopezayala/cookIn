/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lasalle.services.controller.ControllerUsers;
import org.lasalle.services.model.User;

/**
 *
 * @author Claudio
 */
@Path("users")
public class RestUsers {
    @Path("user")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser (@FormParam("name") String name,
                            @FormParam("username") String username,
                            @FormParam("bio") String bio,
                            @FormParam("image") String image,
                            @FormParam("password") String password){
        String out = "";
        try {
            ControllerUsers controller = new ControllerUsers();
            User user = controller.updateUser(name, username, bio, image, password);
            out = """
                  {
                      "id": %s,
                      "name": "%s",
                      "username": "%s",   
                      "bio": "%s"
                  }
                  """;
            out = String.format(out, user.getId(), user.getName(), user.getUsername(), user.getBio());
        } catch (Exception e){
            out = """
                  {"exception" : "%s"}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            
         } catch (Error e){
             out = """
                  {"error" : "%s"}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
         }
        return Response.ok(out).build();

    }
}
