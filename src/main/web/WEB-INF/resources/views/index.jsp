<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form method="get" action="/">
    <input type="text" name="name" value="${name}" placeholder="Введите имя...">
    <button type="submit">Поиск</button>
    <a href="/">Сброс</a>
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
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.admin}</td>
            <td>${user.createDate.toGMTString()}</td>
            <td><a href="/edit/${user.id}">Редактировать</a> | <a href="/delete/${user.id}">Удалить</a></td>
        </tr>

    </c:forEach>
    </tbody>
</table>

<c:if test="${pageCnt > 0}">
<p>
    <a href="/<c:if test="${name != null}">?name=${name}</c:if>">
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

<a href="/add">Создать</a>