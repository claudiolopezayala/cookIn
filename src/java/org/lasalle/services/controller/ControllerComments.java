/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import java.util.LinkedList;
import java.util.List;
import org.lasalle.services.model.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.lasalle.services.model.Ingredient;

/**
 *
 * @author Claudio
 */
public class ControllerComments {
    public Comment getComment (int id)throws Exception{
        String query = "SELECT * FROM comments WHERE id = ?";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                final String text = rs.getString("comment");
                final String publishedDateTime = rs.getString("publishedDateTime");
                final int recipeId = rs.getInt("recipeId");
                final int userId = rs.getInt("userId");
                Comment comment = new Comment(id, text, publishedDateTime, recipeId, userId);
                rs.close();
                pstm.close();
                connMysql.close();
                return comment;
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        throw new Exception("comment6 not found");
    }
    
    public Comment createComment (String comment, int recipeId, int userId)throws Exception{    
        String query = "INSERT INTO comments (comment, recipeId, userId) VALUES (?, ?, ?)";
        
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, comment);
            pstm.setInt(2, recipeId);
            pstm.setInt(3, userId);
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                final int id = rs.getInt(1);
                rs.close();
                pstm.close();
                connMysql.close();
                return getComment(id);
            }
        } catch(Exception | Error e) {
           throw e;
        }
        throw new Error("Ingredient not found");
    }
    
    public List <Comment> getCommentsOfRecipe (int recipeId) throws Exception{
        String query = "select comments.id as id, comments.comment as comment, comments.publishedDateTime as dateTime, users.id as idUser from comments inner join users on comments.userid = users.id inner join recipes on comments.recipeId = recipes.id where recipes.id = ?";
        List<Comment> comments = new LinkedList();
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, recipeId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                final int id = rs.getInt("id");
                final String text = rs.getString("comment");
                final String publishedDateTime = rs.getString("dateTime");
                final int idUser = rs.getInt("idUser");
                
                Comment comment = new Comment(id, text, publishedDateTime, recipeId, idUser );
                comments.add(comment);
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        return comments;
    }
          
}
