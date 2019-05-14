package com.example.myrecipes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        String recipeName = getIntent().getStringExtra("RECIPE_NAME");

        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        Button nextButton = findViewById(R.id.nextButton);
        EditText ingredientNameEditText = findViewById(R.id.ingredientNameEditText);

        recipeNameText.setText(recipeName);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });

        ingredientNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    private void onNextButtonClick(){
        EditText ingredientName = findViewById(R.id.ingredientNameEditText);
        String result = ingredientName.getText().toString();
        if(!result.isEmpty()) {
            ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
            TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
            String recipeName = recipeNameText.getText().toString();
            Intent intent = new Intent(this, UnitOfMeasurementActivity.class);
            intent.putExtra("INGREDIENT_NAME", result);
            intent.putExtra("RECIPE_NAME", recipeName);
            intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.ingredient_name_error),
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 8);
            View view = toast.getView();
            view.setBackgroundColor(Color.RED);
            toast.show();
        }

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
