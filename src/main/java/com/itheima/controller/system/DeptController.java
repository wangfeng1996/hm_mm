package com.itheima.controller.system;

import com.github.pagehelper.PageInfo;

import com.itheima.domain.system.Dept;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.impl.DeptServiceImpl;
import com.itheima.utlis.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

///system/dept?operation=list
@WebServlet("/system/dept")
public class DeptController extends HttpServlet {
    private DeptService deptService = new DeptServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            list(req, resp);
        } else if ("toAdd".equals(operation)) {
            toAdd(req, resp);
        } else if ("save".equals(operation)) {
            save(req, resp);

        } else if ("toEdit".equals(operation)) {
            toEdit(req, resp);

        } else if ("edit".equals(operation)) {
            edit(req, resp);

        } else if ("delete".equals(operation)) {
            delete(req, resp);
        }

    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        获取数据，将数据封装成对象
        Dept dept = BeanUtil.fillBean(req, Dept.class);
//        将数据传入到service
        deptService.save(dept);
//        添加完成后重定向到查询所有的页面
        resp.sendRedirect(req.getContextPath() + "/system/dept?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = req.getParameter("id");
        Dept dept = deptService.findById(id);
        req.setAttribute("dept", dept);
        //加载所有的部门信息放入到deptList
        List<Dept> all = deptService.findAll();
        req.setAttribute("deptList", all);


//        //将数据加载到指定区域，供页面获取

        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        获取要修改的数据
//       获取数据，将数据封装成对象
        Dept dept = BeanUtil.fillBean(req, Dept.class);
//        将对象传入到service
        deptService.update(dept);
//       修改完成后重定向到查询所有的页面
        resp.sendRedirect(req.getContextPath() + "/system/dept?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取页面中的id 值
        String id = req.getParameter("id");
//      将值传递给service
        deptService.delete(id);
//      重定向到list页面
        resp.sendRedirect(req.getContextPath() + "/system/dept?operation=list");
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dept> all = deptService.findAll();
        req.setAttribute("deptList", all);
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(req, resp);

    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取数据
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
//        获取后台数据
        PageInfo all = deptService.findAll(page, size);
        //        将数据放入到session中
        req.setAttribute("page", all);

//        请求转发到制定的页面；
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
