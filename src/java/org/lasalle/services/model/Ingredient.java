/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.model;

/**
 *
 * @author Claudio
 */
public class Ingredient {
    /*CREATE TABLE ingredients (
  id integer PRIMARY KEY auto_increment,
  ingredient varchar(31) not null,
  amount double,
  unitOfMeasure varchar(15),
  recipeId integer not null
);*/
    private int id;
    private String ingredient;
    private double amount;
    private String unitOfMasure;
    private int recipeId;//Stored only as id beacuse recipe has an array of ingredients

    public Ingredient(String ingredient, int recipeId) {
        this.ingredient = ingredient;
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnitOfMasure() {
        return unitOfMasure;
    }

    public void setUnitOfMasure(String unitOfMasure) {
        this.unitOfMasure = unitOfMasure;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    
    
}
