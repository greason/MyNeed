package com.greason.need.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greason on 2015/7/29.
 */
public class ZSDBOperator {

    private String TAG = ZSDBOperator.class.getName();
    private String TABLENAME = DBConstants.ZS_TABLENAME;

    private DBHelper helper;

    public ZSDBOperator(DBHelper helper){
        this.helper = helper;
    }

    private SQLiteDatabase getWritableDatabase(){
        return helper.getWritableDatabase();
    }

    public long insert(ContentValues values){
        long id = -1;
        if(values != null){
            SQLiteDatabase db = getWritableDatabase();
            try{
                db.beginTransaction();
                id = db.insert(TABLENAME , "" ,values);
                db.setTransactionSuccessful();
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
            }finally {
                db.endTransaction();db.close();
            }
        }
        return  id;
    }

    public long delete(String whereClause,String[] whereArgs){
        long id = -1;
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.beginTransaction();
            id = db.delete(TABLENAME, whereClause, whereArgs);
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }finally {
            db.endTransaction();
        }
        return  id;
    }

    public long update(ContentValues values,String whereClause,String[] whereArgs){
        long id = -1;
        SQLiteDatabase db = getWritableDatabase();
        if(values != null) {
            try {
                db.beginTransaction();
                id = db.update(TABLENAME, values, whereClause, whereArgs);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            } finally {
                db.endTransaction();
            }
        }
        return  id;
    }

    public List<Node> query(String[] columns,String selection,String[] selectionArgs,int page , int pagesize){
        List<Node> list = new ArrayList<Node>();
        Cursor cursor = null;
        SQLiteDatabase db = getWritableDatabase();
        try {
            cursor = db.query(TABLENAME, columns , selection, selectionArgs, null, null, null,
                    (page - 1) * pagesize + "," + pagesize);
            for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
               /* Node node = new Node();
                node.setnId(cursor.getInt(cursor.getColumnIndex("nId")));
                node.setpId(cursor.getInt(cursor.getColumnIndex("pId")));
                node.setName(cursor.getString(cursor.getColumnIndex("name")));
                node.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                node.setVisible(cursor.getInt(cursor.getColumnIndex("visible")) == 0 ? false : true);
                list.add(node);*/
            }
        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }finally {
            db.close();
        }
        return list;
    }

  /*  public boolean exist(Node node){
        if(node != null){
            List<Node> list = query(null,"nId = ?" , new String[]{Integer.toString(node.getnId())},1,10);
            if (list.size() >= 1){
                return true;
            }
        }
        return false;
    }*/

    public List<Node> selectBynId(int nId){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        List<Node> list = new ArrayList<Node>();

        Cursor cursor = db.rawQuery("select * from " + TABLENAME + " where nId = ? order by id asc", new String[]{Integer.toString(nId)});

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

          /*  Node node = new Node();
            node.setnId(cursor.getInt(cursor.getColumnIndex("nId")));
            node.setpId(cursor.getInt(cursor.getColumnIndex("pId")));
            node.setName(cursor.getString(cursor.getColumnIndex("name")));
            node.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            node.setVisible(cursor.getInt(cursor.getColumnIndex("visible")) == 0 ? false : true);
            list.add(node);*/
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        return list;
    }

    public List<Node> selectBypId(int pId){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        List<Node> list = new ArrayList<Node>();

        Cursor cursor = db.rawQuery("select * from " + TABLENAME + " where pId = ? order by id asc", new String[]{Integer.toString(pId)});

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

          /*  Node node = new Node();
            node.setnId(cursor.getInt(cursor.getColumnIndex("nId")));
            node.setpId(cursor.getInt(cursor.getColumnIndex("pId")));
            node.setName(cursor.getString(cursor.getColumnIndex("name")));
            node.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            node.setVisible(cursor.getInt(cursor.getColumnIndex("visible")) == 0 ? false : true);
            list.add(node);*/
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        return list;
    }

    public List<Node> selectBynName(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        List<Node> list = new ArrayList<Node>();

        Cursor cursor = db.rawQuery("select * from " + TABLENAME + " where name like '%" + name + "%' order by id asc", null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

          /*  Node node = new Node();
            node.setnId(cursor.getInt(cursor.getColumnIndex("nId")));
            node.setpId(cursor.getInt(cursor.getColumnIndex("pId")));
            node.setName(cursor.getString(cursor.getColumnIndex("name")));
            node.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            node.setVisible(cursor.getInt(cursor.getColumnIndex("visible")) == 0 ? false : true);
            list.add(node);*/
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        return list;
    }
}
