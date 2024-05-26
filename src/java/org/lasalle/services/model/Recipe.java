/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.model;

/**
 *
 * @author Claudio
 */
public class Recipe {
    /*
CREATE TABLE recipes (
  id integer PRIMARY KEY auto_increment,
  title varchar(63) not null unique,
  image varchar(64),
  instructions text not null,
  rations integer,
  timeToCook integer,
  publishedDateTime datetime default now(),
  userId integer not null
);*/
    private int id;
    private String title;
    private String image;
    private String instructions;
    private int rations;
    private int timeToCookInMin;
    private final String publishedDateTime;
    private int userId;

    public Recipe(int id, String title, String instructions, String publishedDateTime, int userId) {
        this.id = id;
        this.title = title;
        this.instructions = instructions;
        this.publishedDateTime = publishedDateTime;
        this.userId = userId;
    }
    public Recipe(String title, String instructions, String publishedDateTime, int userId) {
        this.title = title;
        this.instructions = instructions;
        this.publishedDateTime = publishedDateTime;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getRations() {
        return rations;
    }

    public void setRations(int rations) {
        this.rations = rations;
    }

    public int getTimeToCookInMin() {
        return timeToCookInMin;
    }

    public void setTimeToCookInMin(int timeToCookInMin) {
        this.timeToCookInMin = timeToCookInMin;
    }

    public String getPublishedDateTime() {
        return publishedDateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
    
}
