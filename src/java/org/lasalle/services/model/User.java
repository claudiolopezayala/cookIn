/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.model;

/**
 *
 * @author Claudio
 */

public class User {
    /*
CREATE TABLE users (
  id integer PRIMARY KEY auto_increment,
  name varchar(15) not null,
  username varchar(15) not null unique,
  bio varchar(255),
  image varchar(64),
  password varchar(31) not null
)
    */
    private final int nameMaxLength = 15;
    private final int usernameMaxLength = 15;
    private final int bioMaxLength = 255;
    private final int imageMaxLength = 255;
    private final int passwordMaxLEngth = 31;
    
    private int id;
    private String name;
    private final String username;
    private String bio;
    private String image;
    private String password;

    public User(int id, String name, String username, String bio, String image, String password) throws Exception {
        if(username.length() > usernameMaxLength){
            throw new Exception("username too long");
        }
        this.id = id;
        this.setName(name);
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.setPassword(password);
    }
    
   
    public User(String name, String username, String password) throws Exception{
        if(username.length() > usernameMaxLength){
            throw new Exception("username too long");
        }
        this.name = name;
        this.username = username;
        this.setPassword(password);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) throws Exception {
        if (name.length() > nameMaxLength){
            throw new Exception("name too long");
        }
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public final void setBio(String bio) throws Exception {
        if(bio.length() > bioMaxLength){
            throw new Exception("bio too long");
        }
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public final void setImage(String image) throws Exception  {
        if(image.length() > imageMaxLength){
            throw new Exception("image too large");
        }
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) throws Exception  {
        if (password.length() >  passwordMaxLEngth){
            throw new Exception("password too long");
        }
        this.password = password;
    }
    
    
    
    
}
