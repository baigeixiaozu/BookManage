package cn.bookmanage.test;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

public class TestA {
    @Test
    public void testSuccess(){
        System.out.println("这是一个成功的测试方法");
        int result = 123;
        Assert.assertEquals(123, result);
    }

    @Test
    public void testFail(){
        URL resource = this.getClass().getResource("/test.txt");
        System.out.println("这是一个失败的测试方法");
        int result = 1234;
        Assert.assertEquals(123, result);
    }
}
