package com.example.myrecipes;

public class RecipeItem{

   private String recipeName;
   private long id;
   private String ingredients;
   private String steps;

   public RecipeItem(String recipeName, String ingredients, String steps){
       this.recipeName = recipeName;
       this.ingredients = ingredients;
       this.steps = steps;
   }

   public RecipeItem(String recipeName, String ingredients, String steps, long id){
       this.recipeName = recipeName;
       this.ingredients = ingredients;
       this.steps = steps;
       this.id = id;
   }

    public String getRecipeName() {
        return recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public long getId(){
       return id;
    }

}
