<%--
  Created by IntelliJ IDEA.
  User: ling
  Date: 2020/3/21
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>传统文件上传</h3>
    <form action="user/fileupload1" method="post" enctype="multipart/form-data">
        文件上传：<input type="file" name="upload" /> </br>
        <input type="submit" value="上传">
    </form>

    <h3>SpringMVC文件上传</h3>
    <form action="user/fileupload2" method="post" enctype="multipart/form-data">
        文件上传：<input type="file" name="upload" /> </br>
        <input type="submit" value="上传">
    </form>

    <h3>跨服务器文件上传</h3>
    <form action="user/fileupload3" method="post" enctype="multipart/form-data">
        文件上传：<input type="file" name="upload" /> </br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
