package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ConfirmStepsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_steps);

        String stepInstructions = getIntent().getStringExtra("STEP_INSTRUCTIONS");
        String recipeName = getIntent().getStringExtra("RECIPE_NAME");

        TextView stepsText = findViewById(R.id.stepList);
        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        Button nextButton = findViewById(R.id.nextButton);
        Button saveRecipeButton = findViewById(R.id.saveRecipeButton);

        recipeNameText.setText(recipeName);
        stepsText.setText(populateStepsList());

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });

        saveRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecipeButtonClick();
            }
        });
    }

    private void onNextButtonClick(){
        ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
        ArrayList<String> stepsList = getIntent().getStringArrayListExtra("STEPS_LIST");
        int stepNumber = getIntent().getIntExtra("STEP_NUMBER", 0);
        stepNumber++;
        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        String recipeName = recipeNameText.getText().toString();
        Intent intent = new Intent(this, AddStepActivity.class);
        intent.putExtra("RECIPE_NAME", recipeName);
        intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
        intent.putStringArrayListExtra("STEPS_LIST", stepsList);
        intent.putExtra("STEP_NUMBER", stepNumber);
        startActivity(intent);
    }

    private void saveRecipeButtonClick(){
        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        String recipeName = recipeNameText.getText().toString();
        String steps = populateStepsList();
        String ingredients = populateIngredientList();
        RecipeItem recipe = new RecipeItem(recipeName, ingredients, steps);
        RecipeListManager listManager = new RecipeListManager(this);
        listManager.addItem(recipe);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private String populateStepsList() {
        String stepList = "";
        int stepCounter = 1;
        ArrayList<String> listOfSteps = getIntent().getStringArrayListExtra("STEPS_LIST");
        for (String step : listOfSteps) {
            stepList += "Step " + stepCounter + "\n" + step + "\n\n";
            stepCounter++;
        }
        return stepList;
    }

    private String populateIngredientList() {
        String ingredientList = "";
        ArrayList<String> listOfIngredients = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
        for (String ingredient : listOfIngredients) {
            ingredientList += ingredient + "\n";
        }
        return ingredientList;
    }

}
