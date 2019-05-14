package com.example.myrecipes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRecipeNameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_name);

        Button nextButton = findViewById(R.id.nextButton);
        EditText recipeNameEditText = findViewById(R.id.recipeNameEditText);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });

        recipeNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    private void onNextButtonClick(){
        EditText recipeName = findViewById(R.id.recipeNameEditText);
        String result = recipeName.getText().toString();
        if(!result.isEmpty()){
            ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
            Intent intent = new Intent(this, AddIngredientActivity.class);
            intent.putExtra("RECIPE_NAME", result);
            intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.recipe_name_error),
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
