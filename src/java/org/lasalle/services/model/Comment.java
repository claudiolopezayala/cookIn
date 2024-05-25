/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.model;

/**
 *
 * @author Claudio
 */
public class Comment {
        
    /*CREATE TABLE comments (
  id integer PRIMARY KEY auto_increment,
  comment varchar(255) not null,
  publishedDateTime datetime default now(),
  recipeId integer not null,
  userId integer not null
);*/

    private int id;
    private String comment;
    private String publishedDateTime;
    private int recipeId;//stored only as id because Recipe has an array of comments
    private int idUser;
    
    public Comment(String comment, int recipeId, int idUser) {
        this.comment = comment;
        this.recipeId = recipeId;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPublishedDateTime() {
        return publishedDateTime;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUser() {
        return idUser;
    }

    public void setUser(int user) {
        this.idUser = user;
    }

}
