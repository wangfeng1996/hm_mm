package com.itheima.dao.system;

import com.itheima.domain.system.User;

import java.util.List;

public interface UserDao {

    //    添加
    int save(User user);

    //    删除
    int delete(String id);

    //    修改
    int update(User user);

    //    查询
    List<User> findAll();

    //    根据id查询
    User findById(String id);
}
