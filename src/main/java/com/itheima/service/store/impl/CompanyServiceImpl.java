package com.itheima.service.store.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CompanyDao;
import com.itheima.domain.store.Company;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CompanyService;
import com.itheima.utlis.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class CompanyServiceImpl implements CompanyService {
    //添加
    @Override
    public void save(Company company) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            String id = UUID.randomUUID().toString();
            company.setId(id);
            mapper.save(company);
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
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);
            //3.调用Dao层操作
            companyDao.delete(id);
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
    public void update(Company company) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            mapper.update(company);
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
    public Company findById(String id) {
        SqlSession sqlSession = null;
        Company company = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            company = mapper.findById(id);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
        return company;
    }

    //查询所有的
    @Override
    public List<Company> findAll() {
        SqlSession sqlSession = null;
        List<Company> list = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
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
        PageInfo<Company> pageInfo = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            com.github.pagehelper.Page<Page> pages = PageHelper.startPage(page, size);
            List<Company> list = mapper.findAll();
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
