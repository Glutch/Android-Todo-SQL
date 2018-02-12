package com.packag.app.todoapp;
// Created by oliverjohansson, 2018-02-12.


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
  public static final int DB_VERSION = 4;
  private Context context;

  public DbHelper(Context context) {
    super(context, "todoapp", null, DB_VERSION);
    this.context = context;
  }
  
  @Override
  public void onCreate(SQLiteDatabase db) {
    // When creating the database
    String sqlTodo = "CREATE TABLE Todo ( " +
        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "text VARCHAR(25)," +
        "fk_owner INTEGER," +
        "FOREIGN KEY(fk_owner) REFERENCES Owner(id));";
    
    String sqlOwner = "CREATE TABLE Owner ( " +
        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "name VARCHAR(25))";
    
    db.execSQL(sqlTodo);
    db.execSQL(sqlOwner);
  }
  
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if(oldVersion < newVersion){
      db.delete("Todo", null, null);
      onCreate(db);
    }
  }
  
  public void addTodo(String text, int fk_owner){
    SQLiteDatabase db = getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    
    contentValues.put("text", text);
    contentValues.put("fk_owner", (fk_owner));
    
    db.insert("Todo",null, contentValues);
    db.close();
  }
  
  public void addOwner(String name){
    SQLiteDatabase db = getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("name", name);
    db.insert("Owner",null, cv);
    db.close();
  }
  
  public void deleteTodo(int index){
    SQLiteDatabase db = getReadableDatabase();
    String selection = "id=?";
    String[] selectionArgs = new String[]{Integer.toString(index)};
    db.delete("Todo", selection, selectionArgs);
    db.close();
  }
  
  public void editTodo(int index, String text){
    SQLiteDatabase db = getReadableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("text", text);
    db.update("Todo", cv, "id="+Integer.toString(index), null);
    db.close();
  }
  
  public List<Todo> getAllTodos(){
    List<Todo> todoList = new ArrayList<>();
    SQLiteDatabase db = getReadableDatabase();
    
    Cursor c = db.rawQuery("SELECT * FROM todo",null);
    
    if (c.moveToFirst()) {
      while (!c.isAfterLast()) {
        Todo todo = new Todo();
        
        todo.setId(c.getInt(0));
        todo.setText(c.getString(1));
        todo.setOwner(c.getInt(2));
        
        todoList.add(todo);
        
        c.moveToNext();
      }
    }
    db.close();
    return todoList;
  }
  
  public List<Owner> getAllOwners(){
    List<Owner> owners = new ArrayList<>();
    SQLiteDatabase db = getReadableDatabase();
    Cursor  c = db.rawQuery("SELECT * FROM Owner",null);
    
    if (c.moveToFirst()) {
      while (!c.isAfterLast()) {
        Owner owner = new Owner();
        owner.setId(c.getInt(0));
        owner.setName(c.getString(1));
        owners.add(owner);
        c.moveToNext();
      }
    }
    db.close();
    return owners;
  }
  
}
