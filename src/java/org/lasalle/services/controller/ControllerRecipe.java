/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.lasalle.services.model.Recipe;
import org.lasalle.services.model.User;

/**
 *
 * @author Claudio
 */
public class ControllerRecipe {
    public Recipe GetRecipe (int id)throws Exception {
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
        throw new Exception("user not found");
    }
}
