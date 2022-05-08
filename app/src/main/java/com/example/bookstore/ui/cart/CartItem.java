package com.example.bookstore.ui.cart;

public class CartItem {
    String Title, Author, Image, documentId;
    long Price, Count;

    public CartItem() {
    }

    public CartItem(String title, String author, String image, long price, long count) {
        Title = title;
        Author = author;
        Image = image;
        Price = price;
        Count = count;
    }

    public long getCount() {
        return Count;
    }

    public void setCount(long count) {
        Count = count;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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
