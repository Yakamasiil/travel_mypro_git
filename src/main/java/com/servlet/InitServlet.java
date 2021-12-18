package com.servlet;

import com.alibaba.fastjson.JSONArray;
import com.pojo.Category;
import com.services.ICategoryServices;
import com.services.impl.CategoryServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class InitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ICategoryServices categoryServices = new CategoryServicesImpl();
        //查询分类数据
        List<Category> listc = categoryServices.findAllCategory();

        PrintWriter out = response.getWriter();
        String jsonstr = JSONArray.toJSON(listc).toString();
        System.out.println(jsonstr);

        out.write(jsonstr);

        //把数据放进请求作用域中
//        request.getSession().setAttribute("listc", listc);
//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
