/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import org.lasalle.services.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Claudio
 */
public class ControllerUsers {
    public User updateUser (String name, String username, String bio, String image, String password) throws Exception{     
        String query = "UPDATE users SET name = ?, bio = ?, image = ?, password = ? WHERE username = ?";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, bio);
            pstm.setString(3, image);
            pstm.setString(4, password);
            pstm.setString(5, username);
            pstm.execute();

            pstm.close();
            connMysql.close();
        } catch(Exception | Error e) {
           throw e;
        }
        ControllerAuthentification controller = new ControllerAuthentification();
        return controller.authentificateUser(username, password);
    }
    public User getUser (int id)throws Exception {
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                final String name = rs.getString("name");
                final String username = rs.getString("username");
                final String bio = rs.getString("bio");
                final String image = rs.getString("image");
                User user = new User(id, name, username, bio, image);
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
}
