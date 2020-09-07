package com.itheima.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.service.store.CompanyService;
import com.itheima.service.store.impl.CompanyServiceImpl;
import com.itheima.utlis.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/store/company")
public class CompanyController extends HttpServlet {
    private CompanyService companyService = new CompanyServiceImpl();

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

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");


//        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        companyService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");


    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        companyService.update(company);
        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");

    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = req.getParameter("id");
        Company company = companyService.findById(id);
        //将数据加载到指定区域，供页面获取
        req.setAttribute("company", company);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(req, resp);

    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       获取数据，将数据封装成对象
        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
//        将数据返回值service
        companyService.save(company);
//       重定向list页面，查看数据
        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");

    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        创建对象，获取数据
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
//
//        if (StringUtils.isNotBlank(req.getParameter("page"))) {
//            page = Integer.parseInt(req.getParameter("page"));
//        }
//        if (StringUtils.isNotBlank(req.getParameter("size"))) {
//            size = Integer.parseInt(req.getParameter("size"));
//        }

//        获取数据
        PageInfo all = companyService.findAll(page, size);
        req.setAttribute("page", all);
        req.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
