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
    private final int ingredientMaxLength = 31;
    private final int unitOfMeasureMaxLength = 15;
    
    private int id;
    private String ingredient;
    private double amount;
    private String unitOfMeasure;
    private int recipeId;

    public Ingredient(int id, String ingredient, double amount, String unitOfMeasure, int recipeId) throws Exception {
        this.id = id;
        this.setIngredient(ingredient);
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
        this.recipeId = recipeId;
    }
    
    public Ingredient(String ingredient, int recipeId) throws Exception {
        this.setIngredient(ingredient);
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public final void setIngredient(String ingredient)throws Exception {
        if(ingredient.length() > ingredientMaxLength){
            throw new Exception("ingredient too long");
        }
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public final void setUnitOfMeasure(String unitOfMeasure)throws Exception {
        if (unitOfMeasure.length() > unitOfMeasureMaxLength){
            throw new Exception("unitOfMeasure too large");
        }
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    
    
}
