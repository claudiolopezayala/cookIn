/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.model;

/**
 *
 * @author Claudio
 */
public class Follow {
    /*CREATE TABLE followers (
  id integer PRIMARY KEY auto_increment,
  accountFollowedId integer not null,
  accountThatFollowsId integer not null
);
*/
    private int id;
    private User accountFollowed;
    private User accountThatFollows;

    public Follow(User accountFollowed, User accountThatFollows) {
        this.accountFollowed = accountFollowed;
        this.accountThatFollows = accountThatFollows;
    }

    public int getId() {
        return id;
    }

    public User getAccountFollowed() {
        return accountFollowed;
    }

    public void setAccountFollowed(User accountFollowed) {
        this.accountFollowed = accountFollowed;
    }

    public User getAccountThatFollows() {
        return accountThatFollows;
    }

    public void setAccountThatFollows(User accountThatFollows) {
        this.accountThatFollows = accountThatFollows;
    }
    
    
}
