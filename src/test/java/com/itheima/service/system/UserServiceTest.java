package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.User;
import com.itheima.service.system.impl.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {
    private static UserService userService = null;

    @BeforeClass
    public static void init() {
        userService = new UserServiceImpl();
    }

    // 查询所有的数据
    @Test
    public void findAllTest() {
        UserServiceImpl service = new UserServiceImpl();
        List<User> all = service.findAll();
        System.out.println(all);
    }

    @Test

    public void findAllTest02() {
        PageInfo all = userService.findAll(1, 100);
        System.out.println(all);
    }

    @Test
    public void saveTest() {
        User user= new User();
        user.setUserName("张三");
        userService.save(user);
    }


    @BeforeClass
    public static void destroy() {
        userService = null;
    }


}
