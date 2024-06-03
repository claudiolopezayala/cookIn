/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

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
import org.lasalle.services.controller.ControllerIngredient;
import org.lasalle.services.controller.ControllerRecipe;
import org.lasalle.services.model.Ingredient;
import org.lasalle.services.model.User;

/**
 *
 * @author Claudio
 */
@Path("Ingredients")
public class RestIngredients {
    
    @Path("Ingredients")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ingredients (@QueryParam("recipeId") int recipeId){
        String out = "[";
        try {
            ControllerIngredient controller = new ControllerIngredient();
            final List<Ingredient> ingredients = controller.getRecipeIngredients(recipeId);
            for (Ingredient ingredient : ingredients){
                String ingredientString = """
                                    {
                                        "id": %s,
                                        "ingredient": "%s",
                                        "amount": %s,   
                                        "unitOfMeasure": %s
                                    },
                                    """;
                ingredientString = String.format(ingredientString, ingredient.getId(), ingredient.getIngredient(), ingredient.getAmount(), ingredient.getUnitOfMeasure());
                out += ingredientString;
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
    
    @Path("ingredient")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response recipe (@FormParam("ingredient") String title,
                            @FormParam("amount") String image,
                            @FormParam("unitOfMeasure") String instructions,
                            @FormParam("recipeId") int rations,
                            @FormParam("timeToCook") int timeToCook,
                            @FormParam("userId") int userId ){
        
        try {   
            ControllerRecipe controller = new ControllerRecipe();
            controller.createRecipe(title, image, instructions, rations, timeToCook, userId);
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
        return Response.ok().build();

    }
}
