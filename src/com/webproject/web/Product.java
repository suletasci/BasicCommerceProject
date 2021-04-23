package com.webproject.web;

public class Product {
   public int id;
   public String name;
   public int price;
   public int stockNumber;
   public String image;
public Product(int id, String name, int price, int stockNumber, String image) {
	this.id = id;
	this.name = name;
	this.price = price;
	this.stockNumber = stockNumber;
	this.image = image;
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
public int getStockNumber() {
	return stockNumber;
}
public void setStockNumber(int stockNumber) {
	this.stockNumber = stockNumber;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}

   
   
  
}
