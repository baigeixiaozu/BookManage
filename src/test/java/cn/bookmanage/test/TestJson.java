package cn.bookmanage.test;

import cn.bookmanage.entity.User;
import cn.bookmanage.utils.JsonUtil;
import org.junit.Test;

/**
 * @Author jiyec
 * @Date 2021/5/28 23:22
 * @Version 1.0
 **/
public class TestJson {
    @Test
    public void testJSON(){
        User user = new User(){{
            setId(1);
            setNickname("昵称");
            setStatus(1);
        }};
        String jsonStr = JsonUtil.obj2String(user);
        System.out.println(jsonStr);

        User newUser = JsonUtil.string2Obj(jsonStr, User.class);
        System.out.println(newUser);
    }
}
