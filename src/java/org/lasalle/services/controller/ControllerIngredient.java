/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Claudio
 */
public class ControllerIngredient {
    public void createIngredient (String ingredient, Double amount, String unitOfMeasure, int recipeId)throws Exception{    
        String query = "INSERT INTO ingredients (ingredient, amount, unitOfMeasure, recipeId) VALUES (?, ?, ?, ?)";
        
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, ingredient);
            pstm.setDouble(2, amount);
            pstm.setString(3, unitOfMeasure);
            pstm.setInt(4, recipeId);
            pstm.execute();
        } catch(Exception | Error e) {
           throw e;
        }
    }
}
