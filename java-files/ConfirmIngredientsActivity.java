package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ConfirmIngredientsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ingredients);

        String ingredientName = getIntent().getStringExtra("INGREDIENT_NAME");
        String recipeName = getIntent().getStringExtra("RECIPE_NAME");

        TextView ingredientList = findViewById(R.id.ingredientList);
        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        Button nextButton = findViewById(R.id.nextButton);
        Button noMoreIngredientsButton = findViewById(R.id.noIngredientsButton);

        recipeNameText.setText(recipeName);
        ingredientList.setText(populateIngredientList());

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });

        noMoreIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoMoreIngredientsButtonClick();
            }
        });
    }

    private void onNextButtonClick() {
        ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        String recipeName = recipeNameText.getText().toString();
        Intent intent = new Intent(this, AddIngredientActivity.class);
        intent.putExtra("RECIPE_NAME", recipeName);
        intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
        startActivity(intent);
    }

    private void onNoMoreIngredientsButtonClick() {
        ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
        ArrayList<String> stepsList = new ArrayList<>();
        int stepNumber = 1;
        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        String recipeName = recipeNameText.getText().toString();
        Intent intent = new Intent(this, AddStepActivity.class);
        intent.putExtra("RECIPE_NAME", recipeName);
        intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
        intent.putStringArrayListExtra("STEPS_LIST", stepsList);
        intent.putExtra("STEP_NUMBER", stepNumber);
        startActivity(intent);
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
