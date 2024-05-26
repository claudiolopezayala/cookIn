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
    private int accountFollowedId;
    private int accountThatFollowsId;

    public Follow(int id, int accountFollowedId, int accountThatFollowsId) {
        this.id = id;
        this.accountFollowedId = accountFollowedId;
        this.accountThatFollowsId = accountThatFollowsId;
    }

    public Follow(int accountFollowedId, int accountThatFollowsId) {
        this.accountFollowedId = accountFollowedId;
        this.accountThatFollowsId = accountThatFollowsId;
    }

    public int getId() {
        return id;
    }

    public int getAccountFollowedId() {
        return accountFollowedId;
    }

    public void setAccountFollowed(int accountFollowedId) {
        this.accountFollowedId = accountFollowedId;
    }

    public int getAccountThatFollows() {
        return accountThatFollowsId;
    }

    public void setAccountThatFollows(int accountThatFollowsId) {
        this.accountThatFollowsId = accountThatFollowsId;
    }
    
    
}
