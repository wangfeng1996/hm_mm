package com.itheima.dao.system;

import com.itheima.domain.store.Company;
import com.itheima.domain.system.Dept;

import java.util.List;

public interface DeptDao {

    //    添加
    int save(Dept dept);

    //    删除
    int delete(String id);

    //    修改
    int update(Dept dept);

    //    查询
    List<Dept> findAll();

    //    根据id查询
    Dept findById(String id);


}
