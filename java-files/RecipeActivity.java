package com.example.myrecipes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    private RecipeListManager listManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        listManager = new RecipeListManager(this);

        long id = getIntent().getLongExtra("ID", 0);

        TextView name = findViewById(R.id.newRecipeHeaderTextView);
        TextView ingredients = findViewById(R.id.ingredients);
        TextView steps = findViewById(R.id.steps);
        Button backButton = findViewById(R.id.backButton);

        RecipeItem recipe = listManager.getSpecificItem(id);

        name.setText(recipe.getRecipeName());
        ingredients.setText(recipe.getIngredients());
        steps.setText(recipe.getSteps());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClick();
            }
        });

    }

    private void onBackButtonClick(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
