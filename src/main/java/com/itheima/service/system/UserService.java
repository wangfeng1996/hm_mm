package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.User;

import java.util.List;

public interface UserService {

    //    添加
    void save(User user);

    //    删除
    void delete(String id);

    //    修改
    void update(User user);

    //    查询
    List<User> findAll();

    //    根据id查询
    User findById(String id);

    //    分页查询
    PageInfo findAll(Integer page, Integer size);


}
