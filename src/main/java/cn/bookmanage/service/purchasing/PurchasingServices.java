package cn.bookmanage.service.purchasing;

import cn.bookmanage.dao.MessageDao;
import cn.bookmanage.entity.Book;
import cn.bookmanage.entity.info;

import javax.naming.NamingException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchasingServices {
    public ArrayList<info> fetch(){
        ArrayList<info> sample=new ArrayList<info>();
        MessageDao md=new MessageDao();
        sample=md.fetch();
        if(sample==null){
            return null;
        }
        else {
            return sample;
        }
        }
    public ArrayList<info> fetch_i(){
        ArrayList<info> sample=new ArrayList<info>();
        MessageDao md=new MessageDao();
        sample=md.fetch_i();
        if(sample==null){
            return null;
        }
        else {
            return sample;
        }
    }
    public void store(ArrayList<info> sample)  {
        MessageDao md=new MessageDao();
        md.store(sample);
    }
    public int[] purchase(int id, BigInteger num){
        MessageDao ms=new MessageDao();
        return ms.purchase(6,num);
    }

}
