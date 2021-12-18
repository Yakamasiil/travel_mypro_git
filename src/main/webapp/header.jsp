<!-- 头部 start -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    //json数据分为 json对象和json数组
    //json对象
    // var person = {"id":1, "name":"张三","age":18}
    // console.log(person.name);
    //
    // //json数组
    // var nums = [1,2,3,4,5];
    // var persons = [
    //     {"id":1, "name":"张三","age":18,
    //         "scores":[
    //             {"id":1, "subject":"语文", "score":90}
    //         ]},
    //     {"id":2, "name":"李四","age":28},
    //     {"id":3, "name":"王五","age":38}
    // ]
    // console.log(persons[0].scores[0].score);
    //在页面加载时完成的操作
    $(function () {
        $.ajax({
            type: "get",
            url: "init",
            dataType:"json",
            success: function(data){
                var $first = $("[class='nav-active']");
                //data = data.reverse();
                var listr = "";
                for(var i=0;i<data.length;i++){
                    listr += "<li><a href='router?cid="+data[i].cid+"&action=to_route_list'>"+data[i].cname+"</a></li>";

                    //var $li = $("<li><a href='router?cid="+data[i].cid+"&action=listpage'>"+data[i].cname+"</a></li>");
                    //$first.after($li);
                }
                $first.after($(listr));
            }
        });

    })

</script>

    <header>
        <div class="top_banner">
            <img src="images/top_banner.jpg" alt="">
        </div>
        <div class="shortcut">
            <!-- 未登录状态  -->
            <c:if test="${sessionScope.user==null}">
                <div class="login_out">
                    <a href="login.jsp">登录</a>
                    <a href="register.jsp">注册</a>
                </div>
            </c:if>
            <!-- 登录状态  -->
            <c:if test="${sessionScope.user!=null}">
                <div class="login">
                    <span>欢迎回来，${sessionScope.user.name} </span>
                    <a href="favorite?action=myFList" class="collection">我的收藏</a>
                    <a href="user?action=loginout">退出</a>
                </div>
            </c:if>

        </div>
        <div class="header_wrap">
            <div class="topbar">
                <div class="logo">
                    <!-- <a href="/"><img src="images/logo.jpg" alt=""></a> -->
                </div>
                <div class="search">
                    <input name="" type="text" placeholder="请输入路线名称" class="search_input" autocomplete="off">
                    <a href="javascript:;" class="search-button">搜索</a>
                </div>
                <div class="hottel">
                    <div class="hot_pic">
                        <img src="images/hot_tel.jpg" alt="">
                    </div>
                    <div class="hot_tel">
                        <p class="hot_time">客服热线(9:00-6:00)</p>
                        <p class="hot_num">400-618-9090</p>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- 头部 end -->
     <!-- 首页导航 -->
    <div class="navitem">
        <ul class="nav">
            <li class="nav-active"><a href="index.jsp">首页</a></li>

            <li><a href="favoriterank.html">收藏排行榜</a></li>
        </ul>
    </div>
    