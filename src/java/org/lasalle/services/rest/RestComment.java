/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lasalle.services.controller.ControllerComments;
import org.lasalle.services.model.Comment;

/**
 *
 * @author Claudio
 */
@Path("comments")
public class RestComment {
    @Path("comment")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response comment (@FormParam("comment") String text,
                            @FormParam("recipeId") int recipeId,
                            @FormParam("userId") int userId){
        try {   
            ControllerComments controller = new ControllerComments();
            Comment comment = controller.createComment(text, recipeId, userId);
            String out = """
                  {
                  "id": %s,
                  "comment": "%s",
                  "publishedDateTime": "%s",
                  "recipeId": %s,
                  "userId": %s
                  }
                  """;
            out = String.format(out, comment.getId(), comment.getComment(), comment.getPublishedDateTime(), comment.getRecipeId(), comment.getIdUser());
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
}
