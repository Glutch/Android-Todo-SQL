package com.packag.app.todoapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddTodo extends AppCompatActivity {
  
  private ArrayList<String> list; // create a list
  private ArrayAdapter<String> adapter;
  private DbHelper dbhelper;
  private Switch switchBtn;
  private Owner ownerPos;
  private List<Owner> Owners;
  private EditText textLabel;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_todo);
  
    switchBtn = findViewById(R.id.switchy);
    
    dbhelper = new DbHelper(this);
    
    list = new ArrayList<>();
    Owners = dbhelper.getAllOwners();
    for (int i = 0; i < Owners.size(); i++) {
      list.add(Owners.get(i).getName());
    }
    
    
  }
  
  public void addItemEvent(View view){
    boolean who = switchBtn.isChecked();
    String user = list.get(who ? 1 : 0);
    Toast.makeText(this, user, Toast.LENGTH_SHORT).show();
    
    textLabel = findViewById(R.id.todoText);
    String todoText = textLabel.getText().toString();
    Log.i("ass", todoText);
    
    if(todoText.matches("")){
      Toast.makeText(this, "Write some text pls!", Toast.LENGTH_SHORT).show();
      return;
    } else {
      dbhelper.addTodo(todoText, who ? 1 : 0);
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
    }
    
    
    
  }
}
