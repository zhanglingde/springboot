<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/1
  Time: 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="js/jquery.min.js"></script>

    <script>
        // function test(){
        //     // alert("btn");
        // }
        // 页面加载，绑定单击事件
        $(function(){
            // alert("ten")

            $("#btn").click(function(){
                // alert("hello btn");
                console.log("点击成功");
                // 发送ajax请求
                $.ajax({
                    // 编写json格式，设置属性和值
                    url:"user/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"hehe","password":"123","age":30}',
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        alert("执行成功了");
                        // data服务器端响应的json的数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.age);
                    }
                });

            });
        });

    </script>

</head>
<body>

    <a href="user/testString" >testString</a>

    <br/>

    <a href="user/testVoid" >testVoid</a>

    <br/>

    <a href="user/testModelAndView" >testModelAndView</a>

    <br/>

    <a href="user/testForwardOrRedirect" >testForwardOrRedirect</a>

    <br/>

    <button id="btn" >发送ajax的请求</button>

    </body>
</html>
