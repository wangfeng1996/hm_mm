package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.service.store.impl.CompanyServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

public class CompanyServiceTest {
    private static CompanyService companyService = null;

    @BeforeClass
    public static void init() {
        companyService = new CompanyServiceImpl();
    }

    // 查询所有的数据
    @Test
    public void findAllTest() {
        List<Company> all = companyService.findAll();
        System.out.println(all);
    }

    @Test

    public void findAllTest02() {
        PageInfo<Page> all = companyService.findAll(1, 10);
        System.out.println(all);
    }

    @Test
    public void saveTest() {
        Company company = new Company();
        company.setName("你猜我是谁");
        companyService.save(company);
    }


    @BeforeClass
    public static void destroy() {
        companyService = null;
    }


}
