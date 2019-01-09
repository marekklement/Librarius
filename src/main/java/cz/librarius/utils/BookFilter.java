package cz.librarius.utils;

import cz.librarius.enums.BookCategory;

public class BookFilter {

    private Double price;
    private String title;
    private String author;
    private BookCategory bookCategory;

    public BookFilter(Double price, String title, String author, BookCategory bookCategory) {
        this.price = price;
        this.title = title;
        this.author = author;
        this.bookCategory = bookCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
}