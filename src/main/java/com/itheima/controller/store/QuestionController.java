package com.itheima.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Company;
import com.itheima.domain.store.Course;
import com.itheima.domain.store.Question;
import com.itheima.service.store.CatalogService;
import com.itheima.service.store.CompanyService;
import com.itheima.service.store.CourseService;
import com.itheima.service.store.QuestionService;
import com.itheima.service.store.impl.CatalogServiceImpl;
import com.itheima.service.store.impl.CompanyServiceImpl;
import com.itheima.service.store.impl.CourseServiceImpl;
import com.itheima.service.store.impl.QuestionServiceImpl;
import com.itheima.utlis.BeanUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


@WebServlet("/store/question")
public class QuestionController extends HttpServlet {
    private QuestionService questionService = new QuestionServiceImpl();
    private CompanyService companyService = new CompanyServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            list(req, resp);
        } else if ("toAdd".equals(operation)) {
            toAdd(req, resp);
        } else if ("save".equals(operation)) {
            try {
                save(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("toEdit".equals(operation)) {
            toEdit(req, resp);
        } else if ("edit".equals(operation)) {
            try {
                edit(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("delete".equals(operation)) {
            delete(req, resp);
        } else if ("toExamine".equals(operation)) {
            toExamine(req, resp);
        } else if ("testUpload".equals(operation)) {
            testUpload(req, resp);
        } else if ("toImport".equals(operation)) {
            toImport(req, resp);
        }
    }

    private void toExamine(HttpServletRequest req, HttpServletResponse resp) {
    }


    private void testUpload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        首先判断你上传的是否是文件
        if (ServletFileUpload.isMultipartContent(req)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //4.从request中读取数据
            try {
                List<FileItem> fileItems = fileUpload.parseRequest(req);
                for (FileItem fileItem : fileItems) {
//                    判断下表单时传过来的数据是否是文件类型，isFormField=false
                    if (!fileItem.isFormField()) {
//                        将文件存入到磁盘中
                        fileItem.write(new File(this.getServletContext().getRealPath("upload"), fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void toImport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/pages/store/question/testFileUpload.jsp").forward(req, resp);

    }

    //  ok;
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        questionService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
    }

    //ok
    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //查询要修改的数据findById
        String id = req.getParameter("id");
        Question question = questionService.findById(id);
        //将数据加载到指定区域，供页面获取
        req.setAttribute("question", question);

        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();
        req.setAttribute("companyList", companyList);
        req.setAttribute("catalogList", catalogList);

        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(req, resp);
    }

    //ok
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if (ServletFileUpload.isMultipartContent(req)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //4.从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(req);

            //创建一个标记位,标记当前时候有上传文件的操作
            boolean flag = false;
            for (FileItem item : fileItems) {
                if (StringUtils.isNotBlank(item.getName())) {
                    flag = true;
                    break;
                }
            }

            // --处理form表单提交过来的普通数据
            //将数据获取到，封装成一个对象
            Question question = BeanUtil.fillBean(fileItems, Question.class);
            //调用业务层接口save
            questionService.update(question, flag);

            // --处理form表单提交过来的文件数据
            for (FileItem item : fileItems) {
                //5.当前表单是否是文件表单
                if (!item.isFormField()) {
                    //6.从临时存储文件的地方将内容写入到指定位置
                    item.write(new File(this.getServletContext().getRealPath("upload"), question.getId()));
                }
            }
        }
    }

    //  okok
    private void save(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if (ServletFileUpload.isMultipartContent(req)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //4.从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(req);

            //创建一个标记位,标记当前时候有上传文件的操作
            boolean flag = false;
            for (FileItem item : fileItems) {
//                如果获取到的名字为不为空
                if (StringUtils.isNotBlank(item.getName())) {
                    flag = true;
                    break;
                }
            }

            // --处理form表单提交过来的普通数据
            //将数据获取到，封装成一个对象
            Question question = BeanUtil.fillBean(fileItems, Question.class);
            //调用业务层接口save
            String picture = questionService.save(question, flag);

            // --处理form表单提交过来的文件数据
            for (FileItem item : fileItems) {
                //5.当前表单是否是文件表单
                if (!item.isFormField()) {
                    //6.从临时存储文件的地方将内容写入到指定位置
                    item.write(new File(this.getServletContext().getRealPath("upload"), picture));
                }
            }
        }
        //跳转回到页面list
        resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
    }

    //ok
    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        加载企业
        List<Company> companyList = companyService.findAll();
        req.setAttribute("companyList", companyList);

        //加载学科信息
        List<Catalog> all = catalogService.findAll();
        req.setAttribute("catalogList", all);
        req.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(req, resp);
    }

    //  ok
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        创建对象，获取数据
        int page = 1;
        int size = 10;
        String parameterPage = req.getParameter("page");
        String parameterSize = req.getParameter("size");
        if (parameterPage != null && parameterPage.length() > 0) {
            page = Integer.parseInt(parameterPage);
        }
        if (parameterSize != null && parameterSize.length() > 0) {
            size = Integer.parseInt(parameterSize);
        }
//        获取数据
        PageInfo all = questionService.findAll(page, size);
        req.setAttribute("page", all);
        req.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
