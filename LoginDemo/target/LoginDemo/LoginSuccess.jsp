<%--
  Created by IntelliJ IDEA.
  User: mukesh
  Date: 17/11/21
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
    <h3>Hi <%= request.getAttribute("user")%>,Login is Success.</h3>
    <a href="loginform.html">Go Back</a>
</body>
</html>
