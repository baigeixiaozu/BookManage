package cn.bookmanage.dao;

import cn.bookmanage.utils.JNDIUtils;

import java.sql.Connection;

public class BookDao {
    public void BookIn(){
        try{
            Connection conn= JNDIUtils.getConnection();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
