package com.example.myrecipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myrecipes.domain.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class RecipeListManager {

    private DatabaseHelper dbHelper;

    public RecipeListManager(Context context){
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public List<RecipeItem> getItems() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);

        List<RecipeItem> items = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()) {
                RecipeItem item = new RecipeItem(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.INGREDIENTS)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.STEPS)),
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ID)));

                items.add(item);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return items;
    }

    public RecipeItem getSpecificItem(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.ID + "=" + id, null);
        cursor.moveToFirst();

                RecipeItem item = new RecipeItem(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.INGREDIENTS)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.STEPS)),
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ID)));

        cursor.close();
        return item;
    }

    public void addItem(RecipeItem item) {

        ContentValues newItem = new ContentValues();
        newItem.put(DatabaseHelper.NAME, item.getRecipeName());
        newItem.put(DatabaseHelper.INGREDIENTS, item.getIngredients());
        newItem.put(DatabaseHelper.STEPS, item.getSteps());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DatabaseHelper.TABLE_NAME, null, newItem);
    }

}