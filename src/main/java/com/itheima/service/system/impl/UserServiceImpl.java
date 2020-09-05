package com.itheima.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.DeptDao;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.User;
import com.itheima.dao.system.UserDao;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.UserService;
import com.itheima.utlis.MD5Util;
import com.itheima.utlis.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public void save(User user) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            String id = UUID.randomUUID().toString();
            user.setId(id);
            //密码必须经过加密处理MD5加密
            user.setPassword(MD5Util.md5(user.getPassword()));
            mapper.save(user);
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

    @Override
    public void delete(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            mapper.delete(id);
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

    @Override
    public void update(User user) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            mapper.update(user);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = null;
        List<User> all = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);
            //3.调用Dao层操作
            all = userDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return all;
    }


    @Override
    public User findById(String id) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            //3.调用Dao层操作
            user = mapper.findById(id);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public PageInfo findAll(Integer page, Integer size) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);
            //3.调用Dao层操作
            PageHelper.startPage(page, size);
            List<User> all = userDao.findAll();
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}