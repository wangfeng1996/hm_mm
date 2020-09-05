package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.service.store.impl.CompanyServiceImpl;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.jvm.hotspot.debugger.Page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class poiTest {
    private static CompanyService companyService = null;

    @BeforeClass
    public static void init() {
        companyService = new CompanyServiceImpl();
    }

    // 写对象
    @Test
    public void findAllPoiTest() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
//        创建工作表
        XSSFSheet sheet = wb.createSheet();
        wb.createSheet("这是啥啊");
//        、创建工作表中的行对象
        XSSFRow row = sheet.createRow(1);
//        获取列对象
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("这是一个单元格");


//      创建一个文件对象，作为xlsx的输出文件
        File file = new File("test.xlsx");
//        通过流的方式进行输出
        FileOutputStream os = new FileOutputStream(file);

        wb.write(os);
        wb.close();
        os.close();


    }

    @Test

    public void findAllTest02() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook("test.xlsx");
//        获取工作表
        XSSFSheet s = wb.getSheetAt(0);
//        获取行
        XSSFRow row1 = s.getRow(1);
//        获取单元格
        XSSFCell cell1 = row1.getCell(1);
//        获取单元格中的数据
        String cellValue = cell1.getStringCellValue();
        System.out.println(cellValue);
        wb.close();
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
