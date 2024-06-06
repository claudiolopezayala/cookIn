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
import org.lasalle.services.controller.ControllerIngredient;
import org.lasalle.services.model.Ingredient;

/**
 *
 * @author Claudio
 */
@Path("ingredients")
public class RestIngredients {
    
    @Path("ingredients")
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
                                        "unitOfMeasure": "%s"
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
                            @FormParam("amount") Double amount,
                            @FormParam("unitOfMeasure") String unitOfMeasure,
                            @FormParam("recipeId") int recipeId ){
        
        try {
            ControllerIngredient controller = new ControllerIngredient();
            Ingredient ingredient = controller.createIngredient(title, amount, unitOfMeasure, recipeId);
            String out = """
                  {
                  "id": %s,
                  "ingredient": "%s",
                  "amount": %s,
                  "unitOfMeasure": "%s",
                  "recipeId": %s
                  }
                  """;
            out = String.format(out, ingredient.getId(), ingredient.getIngredient(), ingredient.getAmount(), ingredient.getUnitOfMeasure(), ingredient.getRecipeId());
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
