package cn.bookmanage.service.MarketingSystem;

public class showLackBooks {
    public static String show(int n){
        String name;
        if(n==0){
        name="JSP实用教程（第4版）";
        }else if(n==1){
            name="计算机组成原理微课版";
        } else if(n==2){
            name="软件工程：实践者的研究方法（原书第8版）（本科教学版）";
        } else if(n==3){
            name="web前端设计基础——html5、css3、java(二版)";
        }  else if(n==4){
            name="新目标大学英语系列教材：科技英语教程学生用书";
        }else{
            name="概率论与数理统计及其应用（第2版）";
        }
        String message=name+"\n ";
        return message;
    }
}
