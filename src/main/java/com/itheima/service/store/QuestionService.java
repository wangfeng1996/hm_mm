package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Question;

import java.util.List;

public interface QuestionService {


    String save(Question question, boolean flag);


    void delete(String id);


    void update(Question question,boolean flag);


    Question findById(String id);


    List<Question> findAll();


    PageInfo findAll(int page, int size);
}
