package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;

import java.util.List;

public interface CatalogService {

    void save(Catalog catalog);


    void delete(String id);


    void update(Catalog catalog);


    Catalog findById(String id);


    List<Catalog> findAll();


    PageInfo findAll(int page, int size);
}
