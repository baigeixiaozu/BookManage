package cn.bookmanage.entity;

public class BookBuy {
    private int bookId;
    private int count;
    private String isbn;
    public void setIsbn(String isbn){
        this.isbn=isbn;
    }
    public void setBookId(int id){
        bookId=id;
    }
    public void setCount(int count){
        this.count=count;
    }
    public String getIsbn(){
        return isbn;
    }
    public int getBookId(){
        return bookId;
    }
    public int getCount(){
        return count;
    }
}
