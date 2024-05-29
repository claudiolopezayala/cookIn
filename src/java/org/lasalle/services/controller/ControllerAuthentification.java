/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.lasalle.services.model.User;


/**
 *
 * @author Claudio
 */
public class ControllerAuthentification {
    public User authentificateUser (String username, String password)throws Exception{
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                final int id = rs.getInt("id");
                final String name = rs.getString("name");
                final String bio = rs.getString("bio");
                final String image = rs.getString("image");
                User user = new User(id, name, username, bio, image, password);
                rs.close();
                pstm.close();
                connMysql.close();
                return user;
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        throw new Exception("user not found");
    }
    
    public User createUser (String name, String username, String bio, String image, String password)throws Exception{
        
        String query = "INSERT INTO users (name, username, bio, image, password) VALUES (?, ?, ?, ?, ?)";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, username);
            pstm.setString(3, bio);
            pstm.setString(4, image);
            pstm.setString(5, password);
            pstm.execute();
        } catch(Exception | Error e) {
           throw e;
        }
        return authentificateUser(username, password);
    }
}
