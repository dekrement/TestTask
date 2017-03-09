<%--
  Created by IntelliJ IDEA.
  User: web
  Date: 07.03.2017
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать пользователя</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/add" method="post">
    Имя: <input type="text" name="name"/> <br>
    Возраст: <input type="number" min="1" max="100" name="age"/> <br>
    Админ: <input type="checkbox" name="admin"><br>
    <button type="submit">Создать</button>
</form>
</body>
</html>
