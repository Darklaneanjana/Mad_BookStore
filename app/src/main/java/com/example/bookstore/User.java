package com.example.bookstore;

public class User {
    String Title,Author;
    long Price;

    public User(){}
    public User(String title, String author, long price) {
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
