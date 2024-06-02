/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import com.google.gson.Gson;
import java.util.List;
import org.lasalle.services.model.Comment;
import org.lasalle.services.model.Recipe;
import org.lasalle.services.model.User;

/**
 *
 * @author Claudio
 */
public class Main {
     public static void main(String[] args) {
        ControllerComments controllerComments = new ControllerComments();
        ControllerUsers controllerUsers = new ControllerUsers();
        ControllerRecipe controllerRecipe = new ControllerRecipe();
        ControllerIngredient controllerIngredient = new ControllerIngredient();
        try{
            controllerIngredient.createIngredient("ingredient", 10.52, "ssss", 1);
//            Recipe response = controllerRecipe.getRecipe(1);
//            Gson gson = new Gson();
//            String out = gson.toJson(response);
//            System.out.println(out);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            
         } catch (Error e){
            System.out.println("Error: " + e.getMessage());
          
         }
    }
}
