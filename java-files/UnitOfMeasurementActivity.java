package com.example.myrecipes;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UnitOfMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_of_measurement);

        String ingredientName = getIntent().getStringExtra("INGREDIENT_NAME");
        String recipeName = getIntent().getStringExtra("RECIPE_NAME");

        TextView currentIngredientText = findViewById(R.id.currentIngredientTextView);
        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        Button nextButton = findViewById(R.id.nextButton);

        recipeNameText.setText(recipeName);
        currentIngredientText.setText(ingredientName);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });

        RadioGroup radioGroup = findViewById(R.id.unitOfMeasurementChoices);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onCheckRadioButton(checkedId);
            }
        });

    }

    private void onNextButtonClick(){
        ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("INGREDIENTS_LIST");
        TextView ingredientName = findViewById(R.id.currentIngredientTextView);
        String ingredientNameString = ingredientName.getText().toString();
        ingredientsList.add(ingredientNameString);
        TextView recipeNameText = findViewById(R.id.newRecipeHeaderTextView);
        String recipeName = recipeNameText.getText().toString();
        Intent intent = new Intent(this, ConfirmIngredientsActivity.class);
        intent.putExtra("INGREDIENT_NAME", ingredientNameString);
        intent.putExtra("RECIPE_NAME", recipeName);
        intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
        startActivity(intent);
    }

    private void onCheckRadioButton(int checkedId){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        RadioButton radioButton = (RadioButton) findViewById(checkedId);
        String radioButtonText = radioButton.getText().toString().toLowerCase();
        builder.setTitle(String.format(
                getResources().getString(R.string.amount_of_ingredient_text),
                radioButtonText));


        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);

        builder.setNegativeButton(
                android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }
        );

        builder.setPositiveButton(
                android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String amount = input.getText().toString();
                        if (!amount.isEmpty()) {
                            String unitOfMeasurement = getUnitOfMeasurement();
                            TextView currentIngredientText = findViewById(R.id.currentIngredientTextView);
                            currentIngredientText.setText(amount + " " + unitOfMeasurement + " " + getIntent().getStringExtra("INGREDIENT_NAME").toLowerCase());
                        }
                        else{
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
                }
        );

        builder.show();
    }


    private String getUnitOfMeasurement(){
        String unitOfMeasurement;
        int id = ((RadioGroup)findViewById(R.id.unitOfMeasurementChoices)).getCheckedRadioButtonId();
        if(id == 1){
            unitOfMeasurement = "tsp";
        }
        else if(id == 2){
            unitOfMeasurement = "tbsp";
        }
        else if(id == 3){
            unitOfMeasurement = "cup(s)";
        }
        else if(id == 4){
            unitOfMeasurement = "lb";
        }
        else{
            unitOfMeasurement = "g";
        }
        return unitOfMeasurement;
    }
}