package com.webproject.web;

public class Box {
   public int id;
   public String name;
   public int price;
   public int userId;
public Box(int id, String name, int price, int userId) {
	this.id = id;
	this.name = name;
	this.price=price;
	this.userId = userId;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}

   
   
}
