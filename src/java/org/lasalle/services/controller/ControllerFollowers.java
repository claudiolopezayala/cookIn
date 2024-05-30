/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
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
    
    public List<User> getFollows (String username) throws Exception{
        String query = "select userFollowers.id as id, userFollowers.name as name,userFollowers.username as username, userFollowers.bio as bio, userFollowers.image as image from followers join users userFollowers on userFollowers.id = followers.accountFollowedId join users follows on follows.id = followers.accountThatFollowsId where follows.username = ?;";
        List<User> follows = new LinkedList();
        try {
            ConnectionMysql connMysql = new ConnectionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                final int id = rs.getInt("id");
                final String name = rs.getString("name");
                final String followUsername = rs.getString("username");
                final String bio = rs.getString("bio");
                final String image = rs.getString("image");
                
                User follow = new User(id, name, followUsername, bio, image);
                
                follows.add(follow);
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        } catch(Exception | Error e) {
           throw e;
        }
        return follows;
    }
}
