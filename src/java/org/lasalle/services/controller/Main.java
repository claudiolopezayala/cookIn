/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import com.google.gson.Gson;
import org.lasalle.services.model.User;

/**
 *
 * @author Claudio
 */
public class Main {
     public static void main(String[] args) {
        ControllerFollowers controller = new ControllerFollowers();
        try{
            int count = controller.getFollowsCount("bobsmith");
            Gson gson = new Gson();
            String out = gson.toJson(count);
            System.out.println(out);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            
         } catch (Error e){
            System.out.println("Error: " + e.getMessage());
          
         }
    }
}
