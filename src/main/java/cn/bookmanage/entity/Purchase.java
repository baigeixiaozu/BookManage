package cn.bookmanage.entity;

import lombok.Data;

/**
 * @Author jiyec
 * @Date 2021/6/17 22:25
 * @Version 1.0
 **/
@Data
public class Purchase {
    private int bookId;
    private String name;
    private String isbn;
    private Double price;
    private int need;
}
