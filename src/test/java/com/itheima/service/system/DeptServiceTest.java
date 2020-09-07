package com.itheima.service.system;

import com.github.pagehelper.PageInfo;

import com.itheima.domain.system.Dept;

import com.itheima.service.system.impl.DeptServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

public class DeptServiceTest {
    private static DeptService deptService = null;

    @BeforeClass
    public static void init() {
        deptService = new DeptServiceImpl();
    }

    // 查询所有的数据
    @Test
    public void findAllTest() {
        List<Dept> all = deptService.findAll();
        System.out.println(all);
    }

    @Test

    public void findAllTest02() {
        PageInfo all = deptService.findAll(1, 100);
        System.out.println(all);
    }

    @Test
    public void saveTest() {
        Dept dept = new Dept();
        dept.setDeptName("你猜我是谁");
        deptService.save(dept);
    }


    @BeforeClass
    public static void destroy() {
        deptService = null;
    }


}
