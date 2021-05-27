package cn.bookmanage.entity;

import java.util.HashMap;

public class OrderBookList {
    private String[] bookName;
    private String[] bookCount;

    public String[] getBookName() {
        return bookName;
    }

    public void setBookName(String[] bookName) {
        this.bookName = bookName;
    }

    public String[] getBookCount() {
        return bookCount;
    }

    public void setBookCount(String[] bookCount) {
        this.bookCount = bookCount;
    }

    public OrderBookList(String[] bookName, String[] bookCount) {
        this.bookName = bookName;
        this.bookCount = bookCount;
    }
}

