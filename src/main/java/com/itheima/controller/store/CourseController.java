package com.itheima.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Course;

import com.itheima.service.store.CourseService;

import com.itheima.service.store.impl.CourseServiceImpl;
import com.itheima.utlis.BeanUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/store/course")
public class CourseController extends HttpServlet {
    private CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            this.list(req, resp);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(req, resp);
        } else if ("save".equals(operation)) {
            this.save(req, resp);
        } else if ("toEdit".equals(operation)) {
            this.toEdit(req, resp);
        } else if ("edit".equals(operation)) {
            this.edit(req, resp);
        } else if ("delete".equals(operation)) {
            this.delete(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //进入列表页
        //获取数据
        int page = 1;
        int size = 5;
        String parameterPage = req.getParameter("page");
        String parameterSize = req.getParameter("size");


        if (parameterPage != null && parameterPage.length() > 0) {
            page = Integer.parseInt(parameterPage);
        }
        if (parameterSize != null && parameterSize.length() > 0) {
            size = Integer.parseInt(parameterSize);
        }
        PageInfo all = courseService.findAll(page, size);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/course/list.jsp").forward(req, resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/course/add.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        Course course = BeanUtil.fillBean(req, Course.class, "yyyy-MM-dd");
        //调用业务层接口save
        courseService.save(course);
        //跳转回到页面list
        resp.sendRedirect(req.getContextPath() + "/store/course?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = req.getParameter("id");
        Course course = courseService.findById(id);
        //将数据加载到指定区域，供页面获取
        req.setAttribute("course", course);
        //跳转页面

        req.getRequestDispatcher("/WEB-INF/pages/store/course/update.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Course course = BeanUtil.fillBean(req, Course.class, "yyyy-MM-dd");
        //调用业务层接口save
        courseService.update(course);
        //跳转回到页面list
        resp.sendRedirect(req.getContextPath() + "/store/course?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取id，将id闯入到
        String id = req.getParameter("id");
        //调用业务层接口save
        courseService.delete(id);
        //跳转回到页面list
        resp.sendRedirect(req.getContextPath() + "/store/course?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}