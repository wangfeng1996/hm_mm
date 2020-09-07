package com.itheima.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CatalogDao;
import com.itheima.domain.store.Catalog;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CatalogService;

import com.itheima.utlis.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CatalogServiceImpl implements CatalogService {
    @Override
    public void save(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //id使用UUID的生成策略来获取
            String id = UUID.randomUUID().toString();
            catalog.setId(id);
            catalog.setCreateTime(new Date());

            //3.调用Dao层操作
            catalogDao.save(catalog);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
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
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3.调用Dao层操作
            mapper.delete(id);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public void update(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3.调用Dao层操作
            mapper.update(catalog);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public Catalog findById(String id) {
        SqlSession sqlSession = null;
        Catalog catalog = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3.调用Dao层操作
            catalog = mapper.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
        return catalog;
    }

    @Override
    public List<Catalog> findAll() {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3.调用Dao层操作
            return catalogDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        PageInfo pageInfo = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3.调用Dao层操作
            PageHelper.startPage(page, size);
            List<Catalog> all = catalogDao.findAll();
            pageInfo = new PageInfo(all);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            if (sqlSession != null) {
                TransactionUtil.close(sqlSession);
            }
        }
        return pageInfo;
    }
}