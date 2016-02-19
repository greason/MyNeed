package com.greason.need.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.greason.need.utils.AndroidFiles;

import java.io.File;

/**
 * Created by greason on 2015/7/14.
 */
public class DBHelper extends SQLiteOpenHelper {

    private String TAG = DBHelper.class.getName();

    private static String BASE_PATH = DBConstants.BASE_PATH;
    public static String DATABASE_NAME = DBConstants.DATABASE_NAME;
    private static int NODE_DATABASE_VERSION = 1;

    private Context context;

    public DBHelper(Context context) {
        super(context, BASE_PATH + DATABASE_NAME, null, NODE_DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        File file = new File(BASE_PATH );
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new IllegalStateException("Failed to create database directory: " + file.getParentFile());
            }
        }

        db.execSQL(CREATE_ZS);
        db.execSQL(CREATE_ZG);
        db.execSQL(CREATE_SL);

        AndroidFiles.makeDiscoverable(new File(BASE_PATH + DATABASE_NAME), context);

        Log.e("DBHelper", "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL(DROP_ZS);
            db.execSQL(CREATE_ZG);
            db.execSQL(CREATE_SL);
            onCreate(db);
        }
        Log.e("DBHelper", "onUpgrade");
    }

    private String CREATE_ZS = "CREATE TABLE IF NOT EXISTS [" + DBConstants.ZS_TABLENAME +"]("
            + "[id] INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "[nId] INTEGER NOT NULL DEFAULT 1,"
            + "[pId] INTEGER NOT NULL DEFAULT 0,"
            + "[name] TEXT NOT NULL,"
            + "[visible] INTEGER ,"
            + "[description] INTEGER)" ;

    private String DROP_ZS = "DROP TABLE IF EXISTS [" + DBConstants.ZS_TABLENAME + "]";


    private String CREATE_ZG = "CREATE TABLE IF NOT EXISTS [" + DBConstants.ZG_TABLENAME +"]("
            + "[id] INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "[nId] INTEGER NOT NULL DEFAULT 1,"
            + "[pId] INTEGER NOT NULL DEFAULT 0,"
            + "[name] TEXT NOT NULL,"
            + "[visible] INTEGER ,"
            + "[description] INTEGER)" ;

    private String DROP_ZG = "DROP TABLE IF EXISTS [" + DBConstants.ZG_TABLENAME + "]";


    private String CREATE_SL = "CREATE TABLE IF NOT EXISTS [" + DBConstants.SL_TABLENAME +"]("
            + "[id] INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "[nId] INTEGER NOT NULL DEFAULT 1,"
            + "[pId] INTEGER NOT NULL DEFAULT 0,"
            + "[name] TEXT NOT NULL,"
            + "[visible] INTEGER ,"
            + "[description] INTEGER)" ;

    private String DROP_SL = "DROP TABLE IF EXISTS [" + DBConstants.SL_TABLENAME + "]";

}
