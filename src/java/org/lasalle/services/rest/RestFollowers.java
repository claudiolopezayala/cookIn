/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.lasalle.services.controller.ControllerFollowers;
import org.lasalle.services.controller.ControllerRecipe;
import org.lasalle.services.model.Follow;
import org.lasalle.services.model.User;

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
            ControllerFollowers controller = new ControllerFollowers();
            final int count = controller.getFollowsCount(username);
            out = """
                  {
                      "followsCount": "%s"
                  }
                  """;
            out = String.format(out, count);
            
            
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
    
    @Path("followersCount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response followersCount (@QueryParam("username") String username){
        String out = "";
        try {
            System.out.println(username);
            ControllerFollowers controller = new ControllerFollowers();
            final int count = controller.getFollowersCount(username);
            out = """
                  {
                      "followersCount": "%s"
                  }
                  """;
            out = String.format(out, count);
            
            
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
    
    @Path("follows")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response follows (@QueryParam("username") String username){
        String out = "[";
        try {
            ControllerFollowers controller = new ControllerFollowers();
            final List<User> follows = controller.getFollows(username);
            for (User user : follows){
                String userString = """
                                    {
                                        "id": %s,
                                        "name": "%s",
                                        "username": "%s",   
                                        "bio": "%s"
                                    },
                                    """;
                userString = String.format(userString, user.getId(), user.getName(), user.getUsername(), user.getBio());
                out += userString;
            }        
            out += "]";
            return Response.ok(out).build();
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

    }
  
    @Path("follow")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response follow (@FormParam("accountFollowedId") int accountFollowedId,
                            @FormParam("accountThatFollowsId") int accountThatFollowsId ){
        try {   
            ControllerFollowers controller = new ControllerFollowers();
            Follow follow = controller.addFollow(accountFollowedId, accountThatFollowsId);
            String out = """
                  {
                  "id": %s,
                  "accountFollowedId": %s,
                  "accountThatFollowsId": %s      
                  }
                  """;
            out = String.format(out, follow.getId(), follow.getAccountFollowedId(), follow.getAccountThatFollows());
            return Response.ok(out).build();
        } catch (Exception e){
            String out = """
                  {"exception" : "%s"}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            
         } catch (Error e){
            String out = """
                  {"error" : "%s"}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
         }

    }
    
    @Path("follow")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deltefollow (@FormParam("accountFollowedId") int accountFollowedId,
                            @FormParam("accountThatFollowsId") int accountThatFollowsId ){
        try {   
            ControllerFollowers controller = new ControllerFollowers();
            controller.deleteFollow(accountFollowedId, accountThatFollowsId);
            return Response.ok().build();
        } catch (Exception e){
            String out = """
                  {"exception" : "%s"}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            
         } catch (Error e){
            String out = """
                  {"error" : "%s"}
                  """;
            out = String.format(out, e.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
         }

    }
}
