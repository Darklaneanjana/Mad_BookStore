package com.example.bookstore;

public class CartItem {
    String Title,Author;
    long Price;

    public CartItem(){}
    public CartItem(String title, String author, long price) {
        Title = title;
        Author = author;
        Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }
}
