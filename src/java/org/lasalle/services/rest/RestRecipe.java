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
import java.util.List;
import org.lasalle.services.controller.ControllerComments;
import org.lasalle.services.controller.ControllerFollowers;
import org.lasalle.services.controller.ControllerRecipe;
import org.lasalle.services.controller.ControllerUsers;
import org.lasalle.services.model.Comment;
import org.lasalle.services.model.Recipe;
import org.lasalle.services.model.User;

/**
 *
 * @author Claudio
 */

@Path("recipes")
public class RestRecipe {
    @Path("completeRecipe")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeRecipe (@QueryParam("recipeId") int id){
        String out = "{";
        try {
            ControllerRecipe controllerRecipe = new ControllerRecipe();
            ControllerUsers controllerUsers = new ControllerUsers();
            ControllerComments controllerComments = new ControllerComments();
            
            final Recipe recipe = controllerRecipe.GetRecipe(id);
            final User user = controllerUsers.GetUser(recipe.getUserId());
            final List<Comment> comments = controllerComments.getCommentsOfRecipe(recipe.getId());
            
            out += """
                    "id": %s,
                    "title": "%s",
                    "image": "%s",
                    "instructions": "%s",
                    "rations": %s,
                    "timeToCookInMin": %s,
                    "publishedDateTime": "%s",
                    "user": { 
                        "id": %s,
                        "name": "%s",
                        "username": "%s",   
                        "bio": "%s"
                    },
                   "comments": [
                    """;
            
            out = String.format(out,
                    recipe.getId(),
                    recipe.getTitle(),
                    recipe.getImage(),
                    recipe.getInstructions(),
                    recipe.getRations(),
                    recipe.getTimeToCookInMin(),
                    recipe.getPublishedDateTime(),
                    user.getId(),
                    user.getName(),
                    user.getUsername(),
                    user.getBio());
            for (Comment comment : comments){
                User commentor = controllerUsers.GetUser(comment.getIdUser());
                String commentJson = """
                                     {
                                     "id": %s,
                                     "comment": "%s",
                                     "publishedDateTime": "%s",
                                     "commentor":{
                                        "id": %s,
                                        "name": "%s",
                                        "username": "%s",   
                                        "bio": "%s"
                                     }
                                     },
                                     """;
                out += String.format(commentJson,
                        comment.getId(),
                        comment.getComment(),
                        comment.getPublishedDateTime(),
                        commentor.getId(),
                        commentor.getName(),
                        commentor.getUsername(),
                        commentor.getBio());
            }
            out += "]}";
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
}
