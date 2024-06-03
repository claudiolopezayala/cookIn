/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.lasalle.services.model.Comment;
import org.lasalle.services.model.Ingredient;
import org.lasalle.services.model.Recipe;

/**
 *
 * @author Claudio
 */
public class ControllerIngredient {
    public Ingredient createIngredient (String ingredient, Double amount, String unitOfMeasure, int recipeId)throws Exception{    
        String query = "INSERT INTO ingredients (ingredient, amount, unitOfMeasure, recipeId) VALUES (?, ?, ?, ?)";
        
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, ingredient);
            pstm.setDouble(2, amount);
            pstm.setString(3, unitOfMeasure);
            pstm.setInt(4, recipeId);
            pstm.execute();
            pstm.close();
            connMysql.close();
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                final int id = rs.getInt(1);
                rs.close();
                pstm.close();
                connMysql.close();
                return getIngredient(id);
            }
        } catch(Exception | Error e) {
           throw e;
        }
        throw new Error("Ingredient not found");
    }
    
    public List<Ingredient> getRecipeIngredients (int recipeId)throws Exception{
        String query = "select * from ingredients where recipeId = ?";
        List<Ingredient> ingredients = new LinkedList();
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, recipeId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                final int id = rs.getInt("id");
                final String text = rs.getString("ingredient");
                final Double amount = rs.getDouble("amount");
                final String unitOfMeasure = rs.getString("unitOfMeasure");
                
                Ingredient ingredient = new Ingredient(id, text, amount, unitOfMeasure, recipeId);
                ingredients.add(ingredient);
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        return ingredients;
    }
    
    public Ingredient getIngredient(int id)throws Exception{
        String query = "SELECT * FROM ingredients WHERE id = ?";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                final String text = rs.getString("ingredient");
                final Double amount = rs.getDouble("amount");
                final String unitOfMeasure = rs.getString("unitOfMeasure");
                final int recipeId = rs.getInt("recipeId");
                Ingredient ingredient = new Ingredient(id, text, amount, unitOfMeasure, recipeId);
                rs.close();
                pstm.close();
                connMysql.close();
                return ingredient;
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        throw new Exception("ingredient not found");
    }
}
