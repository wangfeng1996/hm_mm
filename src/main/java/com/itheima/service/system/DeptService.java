package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.domain.system.Dept;

import java.util.List;

public interface DeptService {

    //    添加
    void save(Dept dept);

    //    删除
    void delete(String id);

    //    修改
    void update(Dept dept);

    //    查询
    List<Dept> findAll();

    //    根据id查询
    Dept findById(String id);

    //    分页查询
    PageInfo findAll(Integer page, Integer size);


}
