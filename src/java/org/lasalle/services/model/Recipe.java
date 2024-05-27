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
    private final int titleMaxLength = 63;
    private final int imageMaxLength = 255;
    private final int instructionsMaxLength = 65535;
    
    private int id;
    private String title;
    private String image;
    private String instructions;
    private int rations;
    private int timeToCookInMin;
    private final String publishedDateTime;
    private int userId;

    public Recipe(int id, String title, String image, String instructions, int rations, int timeToCookInMin, String publishedDateTime, int userId) throws Exception {
        this.id = id;
        this.setTitle(title);
        this.image = image;
        this.setInstructions(instructions);
        this.rations = rations;
        this.timeToCookInMin = timeToCookInMin;
        this.publishedDateTime = publishedDateTime;
        this.userId = userId;
    }
    
    public Recipe(String title, String instructions, int userId) throws Exception {
        this.setTitle(title);
        this.setInstructions(instructions);
        this.publishedDateTime = "";
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public final void setTitle(String title) throws Exception{
        if (title.length() > titleMaxLength){
            throw new Exception("title too long");
        }
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public final void setImage(String image) throws Exception {
        if(image.length() > imageMaxLength){
            throw new Exception("image too long");
        }
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public final void setInstructions(String instructions) throws Exception {
        if(instructions.length() > instructionsMaxLength){
            throw new Exception("instructions too long");
        }
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
