package com.example.myrecipes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecipeListManager listManager;
    private RecipeItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView recipeList = findViewById(R.id.recipe_list);
        ImageButton addButton = findViewById(R.id.add_item);
        TextView addText = findViewById(R.id.addRecipeTextView);

        listManager = new RecipeListManager(this);
        adapter = new RecipeItemAdapter(this, listManager.getItems());
        recipeList.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick();
            }
        });

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick();
            }
        });
    }

    private void onAddButtonClick(){
        ArrayList<String> ingredientsList = new ArrayList<>();
        Intent intent = new Intent(this, AddRecipeNameActivity.class);
        intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);
        startActivity(intent);
    }

    private class RecipeItemAdapter extends ArrayAdapter<RecipeItem>{

        private Context context;
        private List<RecipeItem> items;

        private RecipeItemAdapter(Context context, List<RecipeItem> items){
            super(context,-1, items);
            this.context = context;
            this.items = items;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){

            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.recipe_item_layout, parent, false);
            }

            TextView itemTextView = convertView.findViewById(R.id.itemTextView);

            itemTextView.setText(items.get(position).getRecipeName());

            convertView.setTag(items.get(position));

            View.OnClickListener recipeItemOnClickListener = new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    long id = items.get(position).getId();
                    Intent intent = new Intent(context, RecipeActivity.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);

                }
            };

            convertView.setOnClickListener(recipeItemOnClickListener);

            return convertView;

        }

    }
}