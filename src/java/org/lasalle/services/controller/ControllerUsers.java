/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import org.lasalle.services.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Claudio
 */
public class ControllerUsers {
    public User UpdateUser (String name, String username, String bio, String image, String password) throws Exception{
        
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
        } catch(Exception | Error e) {
           throw e;
        }
        ControllerAuthentification controller = new ControllerAuthentification();
        return controller.authentificateUser(username, password);
    }
}
