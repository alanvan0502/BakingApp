package com.alanvan.bakingapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;

import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "bakingrecipes.db";
    private static final int DB_VERSION = 1;

    private static DbHelper sInstance;

    protected SQLiteDatabase db;

    synchronized public static DbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DbHelper(context);
        }
        return sInstance;
    }

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Recipe.class);
            TableUtils.createTable(connectionSource, Ingredient.class);
            TableUtils.createTable(connectionSource, Step.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(RecipeDBContract.SQL_QUERY_CREATE);
//        db.execSQL(IngredientsDBContract.SQL_QUERY_CREATE);
//        db.execSQL(StepDBContract.SQL_QUERY_CREATE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + RecipeDBContract.RecipeEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + StepDBContract.StepEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + IngredientsDBContract.IngredientEntry.TABLE_NAME);
//        onCreate(db);
//    }
}
