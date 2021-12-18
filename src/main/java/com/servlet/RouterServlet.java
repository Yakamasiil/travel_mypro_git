package com.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.RouteImg;
import com.pojo.Router;
import com.services.IRouteImgServices;
import com.services.IRouterServices;
import com.services.impl.RouteImgServicesImpl;
import com.services.impl.RouterServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RouterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String  action = request.getParameter("action");
        PrintWriter out = response.getWriter();

        IRouterServices routerServices = new RouterServicesImpl();
        IRouteImgServices imgServices = new RouteImgServicesImpl();

        if(action.equals("listpage")){
            String cids = request.getParameter("cid");
            String pageNos = request.getParameter("pageNo");
            Integer pageNo = 0;
            if(pageNos==null){
                pageNo = 1;
            }else{
                pageNo = Integer.parseInt(pageNos);
            }

            Integer cid = Integer.parseInt(cids);
            Integer pageSize = 10;
            //查询多少条
            int totalCount = routerServices.totalCount(cid);
            int totalPage = totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize)+1;

            //声明两个参数，上一页和下一页
            Integer prev = pageNo;
            Integer next = pageNo;
            if(pageNo>1){
                prev--;
            }
            if(pageNo<totalPage){
                next++;
            }

            List<Router> listr = routerServices.findAllRouterPage(cid, pageSize, pageNo);

            request.setAttribute("listr",listr);
            request.setAttribute("totalCount",totalCount);
            request.setAttribute("totalPage",totalPage);
            request.setAttribute("cid",cid);
            request.setAttribute("prev",prev);
            request.setAttribute("next",next);
            //转换list为json数据格式（自定义部分转换）
//            JSONArray jary = new JSONArray();
//            for (int i=0;i<listr.size();i++){
//                JSONObject jobj = new JSONObject();
//                jobj.put("name", listr.get(i).getRname());
//                jobj.put("price", listr.get(i).getPrice());
//                jary.add(jobj);
//            }
            //out.write(jary.toJSONString());
            JSONObject jobj = new JSONObject();
            jobj.put("totalPage",totalPage);
            jobj.put("totalCount",totalCount);
            jobj.put("pageNo", pageNo);
            jobj.put("prev",prev);
            jobj.put("next",next);
            jobj.put("list",JSONArray.toJSON(listr));
            out.write(jobj.toJSONString());

//            request.getRequestDispatcher("route_list.jsp").forward(request, response);

        }else if(action.equals("to_route_list")){
            String cids = request.getParameter("cid");
            request.setAttribute("cid",cids);

            //
            request.getRequestDispatcher("route_list.jsp").forward(request, response);

        }else if(action.equals("routeDetail")){
            //得到路线id
            String rids = request.getParameter("rid");
            int rid = Integer.parseInt(rids);
            //根据路线id查询单个路线详情
            Router router = routerServices.findRouterById(rid);
            //根据线路id查询图片展示列表
            List<RouteImg> list = imgServices.findRouteImgByRid(rid);

            request.setAttribute("router", router);
            request.setAttribute("imglist", list);

            request.getRequestDispatcher("route_detail.jsp").forward(request, response);
        }


    }
}
