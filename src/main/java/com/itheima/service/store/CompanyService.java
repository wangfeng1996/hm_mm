package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;

import java.util.List;

public interface CompanyService {
    // 增
    void save(Company company);

    // 删除
    void delete(String id);

    //更新
    void update(Company company);

    //根据id查询
    Company findById(String id);

    //查询所有
    List<Company> findAll();

    //   分页查询数据  (当前页，每页共有多少数据)
    PageInfo findAll(Integer page, Integer size);

}
