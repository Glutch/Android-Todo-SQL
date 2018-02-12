package com.packag.app.todoapp;
// Created by oliverjohansson, 2018-02-12.


public class Todo {
  private int id;
  private String text;
  private int owner;
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getText() {
    return text;
  }
  
  public void setText(String name) {
    this.text = name;
  }
  
  public int getOwner() {
    return owner;
  }
  
  public void setOwner(int owner) {
    this.owner = owner;
  }
}