package cn.bookmanage.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author jiyec
 * @Date 2021/5/18 17:50
 * @Version 1.0
 **/
@Getter
@Setter
public class StoreRecord {
    private long id;
    private String name;
    private long count;
    private String time;
}
