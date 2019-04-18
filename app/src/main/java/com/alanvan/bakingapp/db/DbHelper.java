package com.alanvan.bakingapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "bakingrecipes.db";
    private static final int DATABASE_VERSION = 1;

    private static DbHelper sInstance;

    synchronized public static DbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DbHelper(context);
        }
        return sInstance;
    }

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource arg1) {
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
        if (oldVersion >= newVersion) {
            //Do nothing
            return;
        } else {
            //Upgrade logic here
        }
    }
}
