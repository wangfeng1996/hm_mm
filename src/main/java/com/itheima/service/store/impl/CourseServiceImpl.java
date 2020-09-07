package com.itheima.service.store.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CompanyDao;
import com.itheima.dao.store.CourseDao;
import com.itheima.domain.store.Company;
import com.itheima.domain.store.Course;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CompanyService;
import com.itheima.service.store.CourseService;
import com.itheima.utlis.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class CourseServiceImpl implements CourseService {
    //添加
    @Override
    public void save(Course course) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            String id = UUID.randomUUID().toString();
            course.setId(id);
            mapper.save(course);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }

    }

    //删除
    @Override
    public void delete(String id) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            CourseDao courseDao = MapperFactory.getMapper(sqlSession, CourseDao.class);
            //3.调用Dao层操作
            courseDao.delete(id);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
    }

    //更新（修改）
    @Override
    public void update(Course course) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession,CourseDao.class);
            mapper.update(course);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
    }

    //根据id查询
    @Override
    public Course findById(String id) {
        SqlSession sqlSession = null;
        Course course = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            course = mapper.findById(id);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
        return course;
    }

    //查询所有的
    @Override
    public List<Course> findAll() {
        SqlSession sqlSession = null;
        List<Course> list = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            list = mapper.findAll();
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
        return list;
    }

    //根据分页进行查询
    @Override
    public PageInfo findAll(Integer page, Integer size) {
        SqlSession sqlSession = null;
        PageInfo<Course> pageInfo = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            Page<Page> pages = PageHelper.startPage(page, size);
            List<Course> list = mapper.findAll();
            pageInfo = new PageInfo<>(list);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
        return pageInfo;
    }

}
