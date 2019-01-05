package cz.librarius.utils;

import cz.librarius.enums.BookCategory;

public class BookFilter {

    private String isbn;
    private String title;
    private String author;
    private BookCategory bookCategory;

    public BookFilter(String isbn, String title, String author, BookCategory bookCategory) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.bookCategory = bookCategory;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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