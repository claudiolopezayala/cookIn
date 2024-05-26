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
    
    private final int commentMaxLength = 255;

    private int id;
    private String comment;
    private final String publishedDateTime;
    private int recipeId;
    private int idUser;

    public Comment(int id, String comment, String publishedDateTime, int recipeId, int idUser) throws Exception {
        this.id = id;
        this.setComment(comment);
        this.publishedDateTime = publishedDateTime;
        this.recipeId = recipeId;
        this.idUser = idUser;
    }
    public Comment(String comment, String publishedDateTime, int recipeId, int idUser) throws Exception {
        this.setComment(comment);
        this.publishedDateTime = publishedDateTime;
        this.recipeId = recipeId;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public final void setComment(String comment) throws Exception{
        if (comment.length()>commentMaxLength){
            throw new Exception("comment too long");
        }
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
