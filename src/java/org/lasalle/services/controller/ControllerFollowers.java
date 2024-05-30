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
public class ControllerFollowers {
    
    public int getFollowsCount (String username) throws Exception{
        String query = "SELECT COUNT(*) AS followsCount FROM followers JOIN users ON users.id = followers.accountThatFollowsId WHERE users.username = ?";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                final int followsCount = rs.getInt("followsCount");
                rs.close();
                pstm.close();
                connMysql.close();
                return followsCount;
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        return 0;
    }
    
    public int getFollowersCount (String username) throws Exception{
        String query = "SELECT COUNT(*) AS followersCount FROM followers JOIN users ON users.id = followers.accountFollowedId WHERE users.username = ?";
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                final int followsCount = rs.getInt("followersCount");
                rs.close();
                pstm.close();
                connMysql.close();
                return followsCount;
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        return 0;
    }
}
