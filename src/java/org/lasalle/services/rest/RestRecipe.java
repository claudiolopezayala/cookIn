/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.lasalle.services.controller.ControllerComments;
import org.lasalle.services.controller.ControllerFollowers;
import org.lasalle.services.controller.ControllerIngredient;
import org.lasalle.services.controller.ControllerRecipe;
import org.lasalle.services.controller.ControllerUsers;
import org.lasalle.services.model.Comment;
import org.lasalle.services.model.Ingredient;
import org.lasalle.services.model.Recipe;
import org.lasalle.services.model.User;

/**
 *
 * @author Claudio
 */

@Path("recipes")
public class RestRecipe {
    
    @Path("recipes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response recipes (@QueryParam("userId") int userId){
        String out = "[";
        try {
            ControllerRecipe controller = new ControllerRecipe();
            final List<Recipe> recipes = controller.getUsersRecipes(userId);
            for (Recipe recipe : recipes){
                String userString = """
                                    {
                                        "id": %s,
                                        "title": "%s",
                                        "image": "%s",   
                                        "instructions": "%s",
                                        "rations": %s,
                                        "timeToCook": %s,
                                        "publishedDateTime": "%s",
                                        "userId": %s
                                    },
                                    """;
                Gson gson = new Gson();
                System.out.println(gson.toJson(recipe.getInstructions()));
                userString = String.format(userString, recipe.getId(), recipe.getTitle(), recipe.getImage(), recipe.getInstructions(), recipe.getRations(), recipe.getTimeToCookInMin(), recipe.getPublishedDateTime(), recipe.getUserId());
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
    
    @Path("recipe")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response recipe (@FormParam("title") String title,
                            @FormParam("image") String image,
                            @FormParam("instructions") String instructions,
                            @FormParam("rations") int rations,
                            @FormParam("timeToCook") int timeToCook,
                            @FormParam("userId") int userId ){
        String out = "";
        try {   
            ControllerRecipe controller = new ControllerRecipe();
            Recipe recipe = controller.createRecipe(title, image, instructions, rations, timeToCook, userId);
            out = """
                  {
                  "id" : %s,
                  "title" : "%s",
                  "image" : "%s",
                  "instructions" : "%s",
                  "rations" : %s,
                  "timeToCook" : %s,
                  "publishedDateTime": "%s",
                  "userId" : %s,
                  }
                  """;
            out = String.format(out, recipe.getId(), recipe.getImage(), recipe.getTitle(), recipe.getInstructions(), recipe.getRations(), recipe.getTimeToCookInMin(), recipe.getPublishedDateTime(), recipe.getUserId());
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
    
    @Path("completeRecipe")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeRecipe (@QueryParam("recipeId") int id){
        String out = "{";
        try {
            ControllerRecipe controllerRecipe = new ControllerRecipe();
            ControllerUsers controllerUsers = new ControllerUsers();
            ControllerComments controllerComments = new ControllerComments();
            ControllerIngredient cntrollerIngredient = new ControllerIngredient();
            
            final Recipe recipe = controllerRecipe.getRecipe(id);
            final User user = controllerUsers.getUser(recipe.getUserId());
            final List<Comment> comments = controllerComments.getCommentsOfRecipe(recipe.getId());
            final List<Ingredient> ingredients = cntrollerIngredient.getRecipeIngredients(recipe.getId());
            
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
                   "Ingredients":[
                    """;
            Gson gson = new Gson();
            System.out.println(gson.toJson(recipe.getInstructions()));
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
            for (Ingredient ingredient : ingredients){
                String ingredientJSON = """
                                        {
                                        "id": %s,
                                        "ingredient": "%s",
                                        "amount": %s,
                                        "unitOfMeasure": "%s",
                                        },
                                        """;
                out += String.format(ingredientJSON, ingredient.getId(), ingredient.getIngredient(), ingredient.getAmount(), ingredient.getUnitOfMeasure());
            }
            out += """
                   ],
                   "comments": [
                   """;
            
            for (Comment comment : comments){
                User comentator = controllerUsers.getUser(comment.getIdUser());
                String commentJson = """
                                     {
                                     "id": %s,
                                     "comment": "%s",
                                     "publishedDateTime": "%s",
                                        "commentator":{
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
                        comentator.getId(),
                        comentator.getName(),
                        comentator.getUsername(),
                        comentator.getBio());
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
