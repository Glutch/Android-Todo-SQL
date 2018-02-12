package com.packag.app.todoapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  
  private ListView listView;
  private ArrayList<String> list;
  private ArrayAdapter<String> arrayAdapter;
  private DbHelper dbhelper;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    dbhelper = new DbHelper(this);
    
    if(dbhelper.getAllOwners().size() <= 0) {
      dbhelper.addOwner("Oliver");
      dbhelper.addOwner("Vanessa");
    }
    
    listView = findViewById(R.id.todoView);
    list = new ArrayList<>();
    List<Todo> todoList = dbhelper.getAllTodos();
    for (int i = 0; i < todoList.size(); i++) {
      list.add(todoList.get(i).getText());
    }
    arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    listView.setAdapter(arrayAdapter);
    
    
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, ViewItem.class);
        intent.putExtra("ITEM_POS", position);
        startActivity(intent);
      }
    });
  }
  
  public void newItemEvent(View view){
    Intent intent = new Intent(this, AddTodo.class);
    startActivity(intent);
  }
}
