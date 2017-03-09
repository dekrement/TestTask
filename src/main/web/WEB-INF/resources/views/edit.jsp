<%--
  Created by IntelliJ IDEA.
  User: web
  Date: 07.03.2017
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование</title>
</head>
<body>
<form action="<%=request.getContextPath()%> /edit" method="post">
    <input type="hidden" name="id" value="${user.id}">
    Имя: <input type="text" value="${user.name}" name="name"/> <br>
    Возраст: <input type="number" min="1" max="100" value="${user.age}" name="age"> <br>
    Админ: <input type="checkbox" <c:if test="${user.admin}"> checked </c:if>name="admin"> <br>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>
