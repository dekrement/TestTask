<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form method="get" action="<%=request.getContextPath()%>/">
    <input type="text" name="name" value="${requestScope.name}" placeholder="Введите имя...">
    <button type="submit">Поиск</button>
    <a href="<%=request.getContextPath()%>/">Сброс</a>
</form>
<table>
    <thead>
    <th>Имя</th>
    <th>Возраст</th>
    <th>Админ</th>
    <th>Дата создания</th>
    <th>Действия</th>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.users}" var="myUser">
        <tr>
            <td>${myUser.name}</td>
            <td>${myUser.age}</td>
            <td>${myUser.admin}</td>
            <td>${myUser.createDate.toGMTString()}</td>
            <td><a href="<%=request.getContextPath()%>/edit/${myUser.id}">Редактировать</a> | <a href="<%=request.getContextPath()%>/delete/${myUser.id}">Удалить</a></td>
        </tr>

    </c:forEach>
    </tbody>
</table>

<c:if test="${pageCnt > 0}">
<p>
    <a href="<%=request.getContextPath()%>/<c:if test="${name != null}">?name=${name}</c:if>">
        <c:choose>
            <c:when test="${page == 0}">
            <b>1</b>
            </c:when>
            <c:otherwise>
                1
            </c:otherwise>
        </c:choose>
    </a>


        <c:forEach begin="1" end="${pageCnt}" varStatus="loop">
            <a href="?page=${loop.index}<c:if test="${name != null}">&name=${name}</c:if> ">
                <c:choose>
                    <c:when test="${loop.index == page}">
                        <b>${loop.index + 1}</b>
                    </c:when>
                    <c:otherwise>
                        ${loop.index + 1}
                    </c:otherwise>
                </c:choose>
            </a>
        </c:forEach>

</p>
</c:if>

<a href="<%=request.getContextPath()%>/add">Создать</a>