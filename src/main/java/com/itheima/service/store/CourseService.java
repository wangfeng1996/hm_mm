package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.domain.store.Course;

import java.util.List;

public interface CourseService {
    // 增
    void save(Course course);

    // 删除
    void delete(String id);

    //更新
    void update(Course course);

    //根据id查询
    Course findById(String id);

    //查询所有
    List<Course> findAll();

    //   分页查询数据  (当前页，每页共有多少数据)
    PageInfo findAll(Integer page, Integer size);

}
