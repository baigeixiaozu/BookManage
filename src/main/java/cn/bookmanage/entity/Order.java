package cn.bookmanage.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体
 *
 * @Author jiyec
 * @Date 2021/5/7 9:46
 * @Version 1.0
 **/
@Getter
@Setter
public class Order {
    private long Order_id;
    private long id;
    private String name;
    private String author;
    private String publish;
    private String isbn;
    private double price;
    private long count;
    private int need;

}
