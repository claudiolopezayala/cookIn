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
import org.lasalle.services.controller.ControllerRecipe;

/**
 *
 * @author Claudio
 */
@Path("Ingredients")
public class RestIngredients {
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
