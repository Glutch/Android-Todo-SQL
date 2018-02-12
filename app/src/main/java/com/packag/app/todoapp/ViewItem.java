package com.packag.app.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewItem extends AppCompatActivity {
  
  private TextView textLabel;
  private TextView ownerLabel;
  private EditText newTextLabel;
  private Button editBtn;
  private Button deleteBtn;
  private Button saveBtn;
  private int pos;
  private Todo todo;
  private DbHelper dbhelper;
  private ArrayList<Todo> list;
  private ArrayList<Owner> owners;
  private Owner owner;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_item);
  
    textLabel = findViewById(R.id.textLabel);
    ownerLabel = findViewById(R.id.ownerLabel);
    editBtn = findViewById(R.id.editBtn);
    deleteBtn = findViewById(R.id.deleteBtn);
    saveBtn = findViewById(R.id.saveBtn);
    newTextLabel = findViewById(R.id.newTextLabel);
  
    dbhelper = new DbHelper(this);
    list = (ArrayList<Todo>) dbhelper.getAllTodos();
  
    Intent intent = getIntent();
    pos = intent.getIntExtra("ITEM_POS", 0);
  
    todo = list.get(pos);
    textLabel.setText(todo.getText());
  
    owners = (ArrayList<Owner>) dbhelper.getAllOwners();
    owner = owners.get(todo.getOwner());
    ownerLabel.setText(owner.getName());
  }
  
  public void deleteEvent(View view) {
    dbhelper.deleteTodo(list.get(pos).getId());
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }
  
  public void editEvent(View view) {
    editBtn.setVisibility(View.INVISIBLE);
    deleteBtn.setVisibility(View.INVISIBLE);
    textLabel.setVisibility(View.INVISIBLE);
    saveBtn.setVisibility(View.VISIBLE);
    newTextLabel.setVisibility(View.VISIBLE);
    
    textLabel.setText(todo.getText(), TextView.BufferType.EDITABLE);
  }
  
  public void saveEvent(View view) {
    dbhelper.editTodo(list.get(pos).getId(), newTextLabel.getText().toString());
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }
}
