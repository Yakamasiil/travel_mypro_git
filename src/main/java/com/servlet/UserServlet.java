package com.servlet;

import com.pojo.Category;
import com.pojo.User;
import com.services.ICategoryServices;
import com.services.IUserServices;
import com.services.impl.CategoryServicesImpl;
import com.services.impl.UserServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request是处理用户请求的，response是处理用户响应的
        request.setCharacterEncoding("utf-8");  //设置请求编码集
//        response.setCharacterEncoding("GBK");   //设置响应编码集
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //因为登录、注册等的方法是写在services里面，所以要创建用户的service对象
        IUserServices userServices = new UserServicesImpl();
        //要得到业务动作
        String action = request.getParameter("action");

        //执行登录业务
        if(action.equals("login")){
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            //req.getParameterValues("hobbies")     获取复选框的值

            ICategoryServices categoryServices = new CategoryServicesImpl();
            //调用登录的方法
            User user = userServices.login(userName, password);
            if(user!=null){
                //登录成功就跳转到首页---转发，同时把用户信息存储在session中
                request.getSession().setAttribute("user",user);
                //查询分类数据
                List<Category> listc = categoryServices.findAllCategory();
                //把数据放进请求作用域中
                request.setAttribute("listc", listc);

//                request.getRequestDispatcher("index.jsp").forward(request, response);
                out.write("true");
            }else{
                //失败就还是当前登录页面---重定向
                //resp.sendRedirect("login.jsp");
//                out.print("<script>alert('登录失败！');location.href='login.jsp';</script>");
                out.write("false");
            }

        }else if(action.equals("register")){
            //接收页面请求的参数
            User user = new User();
            user.setUserName(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setName(request.getParameter("name"));
            user.setTelephone(request.getParameter("telephone"));
            user.setBirthday(request.getParameter("birthday"));
            String sex =  request.getParameter("sex");
            user.setSex(Integer.parseInt(sex)); //从页面获得的所有数据都是string的，需要强转
            boolean flag = userServices.register(user);
            if(flag){
                out.write("true");
            }else{
                out.write("false");
            }
//            if(userServices.register(user)){
//                response.sendRedirect("register_ok.html");
////            out.print("<script>alert('register success!');location.href='register.jsp';</script>");
////            request.getRequestDispatcher("index.html").forward(request, response);
//            }else{
//                response.sendRedirect("register.jsp");
////            out.print("<script>alert('登录失败！');location.href='login.jsp';</script>");
//            }
        }else if(action.equals("loginout")){
            //删除会话中的user信息
            request.getSession().removeAttribute("user");
            response.sendRedirect("login.jsp");
        }else if(action.equals("checkUser")){
            String uname = request.getParameter("uname");

            boolean flag = userServices.checkUserName(uname);

            out.write(flag+"");
        }else if (action.equals("checkCode")){
            //得到页面输入的验证码
            String code = request.getParameter("code");
            //得到系统产生的验证码
            String syscode = request.getSession().getAttribute("CHECKCODE_SERVER").toString();

            if(code.equals(syscode)){
                out.write("true");
            }else{
                out.write("false");
            }
        }else if(action.equals("activeAccount")){
            String code = request.getParameter("code");

            //1、根据用户点击的激活链接，得到code激活码，判断是否一致
            User user = userServices.findUserByCode(code);
            if (user!=null) {
                //2、更新用户的激活状态
                int count = userServices.updateUserById(user.getUid());
                out.print("<h1>激活账户成功，请<a href='login.jsp'>登录</a></h1>");
            }

        }

    }
}
