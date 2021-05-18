package cn.bookmanage.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author jiyec
 * @Date 2021/5/17 20:29
 * @Version 1.0
 **/
@Getter
@Setter
public class Book {
    private long id;
    private String name;
    private String author;
    private String publish;
    private String isbn;
    private double price;
    private long count;

}
