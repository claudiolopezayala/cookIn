/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import org.lasalle.services.model.Recipe;

/**
 *
 * @author Claudio
 */
public class ControllerRecipe {
    public List<Recipe> getUsersRecipes (int userId) throws Exception{
        String query = "select * from recipes where userId = ?";
        List<Recipe> recipes = new LinkedList();
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                final int id = rs.getInt("id");
                final String title = rs.getString("title");
                final String image = rs.getString("image");
                final String instructions = rs.getString("instructions");
                final int rations = rs.getInt("rations");
                final int timeToCook = rs.getInt("timeToCook");
                final String publishedDateTime = rs.getString("publishedDateTime");
                
                Recipe recipe = new Recipe(id, title, image, instructions, rations, timeToCook, publishedDateTime, userId);
                recipes.add(recipe);
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        return recipes;
    }
    
    public Recipe getRecipe (int id)throws Exception {
        String query = "SELECT * FROM recipes WHERE id = ?";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                final String title = rs.getString("title");
                final String image = rs.getString("image");
                final String instructions = rs.getString("instructions");
                final int rations = rs.getInt("rations");
                final int timeToCook = rs.getInt("timeToCook");
                final String publishedDateTime = rs.getString("publishedDateTime");
                final int userId = rs.getInt("userId");
                Recipe recipe = new Recipe(id, title, image, instructions, rations, timeToCook, publishedDateTime, userId);
                rs.close();
                pstm.close();
                connMysql.close();
                return recipe;
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        throw new Exception("recipe not found");
    }
    
    public Recipe createRecipe (String title, String image, String instructions, int rations, int timeToCook, int userId)throws Exception{    
        String query = "INSERT INTO recipes (title, image, instructions, rations, timeToCook, userId) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, title);
            pstm.setString(2, image);
            pstm.setString(3, instructions);
            pstm.setInt(4, rations);
            pstm.setInt(5, timeToCook);
            pstm.setInt(6, userId);
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                final int id = rs.getInt(1);
                rs.close();
                pstm.close();
                connMysql.close();
                return getRecipe(id);
            }
        } catch(Exception | Error e) {
           throw e;
        }
        throw new Error("Recipe not found");
    }
}
