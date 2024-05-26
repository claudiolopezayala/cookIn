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

    public Follow(int id, int accountFollowedId, int accountThatFollowsId)  throws Exception  {
        this.id = id;
        this.setAccountFollowed(accountFollowedId);
        this.setAccountThatFollows(accountThatFollowsId);
    }

    public Follow(int accountFollowedId, int accountThatFollowsId)  throws Exception  {
        this.setAccountFollowed(accountFollowedId);
        this.setAccountThatFollows(accountThatFollowsId);
    }

    public int getId() {
        return id;
    }

    public int getAccountFollowedId() {
        return accountFollowedId;
    }

    public final void setAccountFollowed(int accountFollowedId) throws Exception {
        if (accountFollowedId == this.accountThatFollowsId){
            throw new Exception("an account can't follow itself");
        }
        this.accountFollowedId = accountFollowedId;
    }

    public int getAccountThatFollows() {
        return accountThatFollowsId;
    }

    public final void setAccountThatFollows(int accountThatFollowsId)  throws Exception  {
        if (accountThatFollowsId == this.accountFollowedId){
            throw new Exception("an account can't follow itself");
        }
        this.accountThatFollowsId = accountThatFollowsId;
    }
    
    
}
