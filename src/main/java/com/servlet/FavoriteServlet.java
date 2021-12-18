package com.servlet;

import com.alibaba.fastjson.JSONObject;
import com.pojo.Favorite;
import com.pojo.Router;
import com.pojo.User;
import com.services.IFavoriteServices;
import com.services.impl.FavoriteServicesImpl;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FavoriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        IFavoriteServices favoriteServices = new FavoriteServicesImpl();


        if(action.equals("showbtn")){
            String rids = request.getParameter("rid");
            int rid = Integer.parseInt(rids);
            //从会话中获得用户id
            int uid = ((User)request.getSession().getAttribute("user")).getUid();
            Favorite favorite = favoriteServices.findFavoriteByRidAndUid(uid, rid);

            //查询收藏次数
            int count = favoriteServices.fCount(rid);

            JSONObject jobj = new JSONObject();
            jobj.put("count",count);
            jobj.put("rid",rid);
            if(favorite!=null){
                jobj.put("flag", true);
            }else{
                jobj.put("flag", false);
            }
            out.write(jobj.toJSONString());
        }else if(action.equals("addFavorite")){
            String rids = request.getParameter("rid");
            int rid = Integer.parseInt(rids);
            //从会话中获得用户id
            int uid = ((User)request.getSession().getAttribute("user")).getUid();

            Favorite favorite = new Favorite();
            //创建router对象
            Router router = new Router();
            router.setRid(rid);
            User user = new User();
            user.setUid(uid);

            //给收藏的对象赋值
            favorite.setRouter(router);
            favorite.setUser(user);
            int count = favoriteServices.addFavorite(favorite);
            if(count >0){
                out.write("true");
            }else {
                out.write("false");
            }
        }else if(action.equals("myFList")){

            //从会话中获得用户id
            int uid = ((User)request.getSession().getAttribute("user")).getUid();

            List<Favorite> list = favoriteServices.findAllFavoriteByUid(uid);
            request.setAttribute("list", list);
            request.getRequestDispatcher("myfavorite.jsp").forward(request, response);
        }


    }
}
