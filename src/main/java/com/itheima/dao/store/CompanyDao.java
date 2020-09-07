package com.itheima.dao.store;

import com.itheima.domain.store.Company;

import java.util.List;

public interface CompanyDao {
    // 增
    int save(Company company);

    // 删除
    int delete(String id);

    //更新
    int update(Company company);

    //根据id查询
    Company findById(String id);

    //查询所以的
    List<Company> findAll();
}
