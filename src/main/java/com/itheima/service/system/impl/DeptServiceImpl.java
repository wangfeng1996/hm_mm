package com.itheima.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.DeptDao;
import com.itheima.domain.store.Company;
import com.itheima.domain.system.Dept;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.DeptService;
import com.itheima.utlis.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class DeptServiceImpl implements DeptService {
    @Override
    public void save(Dept dept) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            String id = UUID.randomUUID().toString();
            dept.setId(id);
            mapper.save(dept);
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
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
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
    public void update(Dept dept) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            mapper.update(dept);
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
    public List<Dept> findAll() {

        SqlSession sqlSession = null;

        List<Dept> dept = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            dept = mapper.findAll();
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
        return dept;
    }

    @Override
    public Dept findById(String id) {
        SqlSession sqlSession = null;
        Dept dept = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            dept = mapper.findById(id);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }

        }


        return dept;
    }

    @Override
    public PageInfo findAll(Integer page, Integer size) {
        SqlSession sqlSession = null;
        PageInfo<Dept> pageInfo = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            PageHelper.startPage(page, size);
            List<Dept> all = mapper.findAll();
            pageInfo = new PageInfo<>(all);
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
