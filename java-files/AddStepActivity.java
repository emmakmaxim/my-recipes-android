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

public class AddStepActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_step);

        String recipeName = getIntent().getStringExtra("RECIPE_NAME");

        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        TextView stepHeader = findViewById(R.id.stepLabel);
        TextView stepInstructions = findViewById(R.id.addStepInstructions);
        Button nextButton = findViewById(R.id.nextButton);
        EditText stepEditText = findViewById(R.id.stepEditText);

        int stepNumber = getIntent().getIntExtra("STEP_NUMBER", 0);

        stepHeader.setText(
                String.format(
                        getResources().getString(R.string.step_label),
                        stepNumber
                )
        );

        stepInstructions.setText(
                String.format(
                getResources().getString(R.string.add_step_text),
                stepNumber)
        );

        recipeNameText.setText(recipeName);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });

        stepEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    private void onNextButtonClick(){
        EditText stepEditText = findViewById(R.id.stepEditText);
        String step = stepEditText.getText().toString();
        if(!step.isEmpty()) {
            ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
            ArrayList<String> stepsList = getIntent().getStringArrayListExtra("STEPS_LIST");
            int stepNumber = getIntent().getIntExtra("STEP_NUMBER", 0);
            TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
            String recipeName = recipeNameText.getText().toString();
            stepsList.add(step);
            Intent intent = new Intent(this, ConfirmStepsActivity.class);
            intent.putExtra("RECIPE_NAME", recipeName);
            intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
            intent.putStringArrayListExtra("STEPS_LIST", stepsList);
            intent.putExtra("STEP_NUMBER", stepNumber);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.step_error),
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
