<%--
  Created by IntelliJ IDEA.
  User: zuz-schwarzova
  Date: 15. 12. 2016
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Revisions">
<jsp:attribute name="body">

    <a href="${pageContext.request.contextPath}/admin/revision/new" class="btn btn-success">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Create
    </a>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>revisionDate</th>
            <th>machine</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${revisions}" var="revision">
            <tr>
                <td>${revision.id}</td>
                <td><fmt:formatDate value="${revision.revisionDate}" type="date" dateStyle="medium"/></td>
                <td><c:out value="${revision.machine.name}"/></td>
                <td>
                    <a href="/pa165/admin/revision/view/${revision.id}" class="btn btn-default">Details</a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/revision/delete/${revision.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:mainPageTag>