<%--
    Author:  Josef Plch
    Created: 2016-12-16
    Version: 2016-12-17
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Users">
<jsp:attribute name="body">
    <a href="${pageContext.request.contextPath}/admin/rental_user/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New user
    </a>

    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Username</th>
                <th>E-mail</th>
                <th>Roles</th>
                <th>Legal personality</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${rentalUsers}" var="rentalUser">
            <tr>
                <td>${rentalUser.id}</td>
                <td><c:out value="${rentalUser.name}"/></td>
                <td><c:out value="${rentalUser.username}"/></td>
                <td><c:out value="${rentalUser.email}"/></td>
                <td><c:out value="${rentalUser.roles}"/></td>
                <td><c:out value="${rentalUser.legalPersonality}"/></td>
                <td>
                    <a href="/pa165/admin/rental_user/view/${rentalUser.id}" class="btn btn-primary">Detail</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/rental_user/update/${rentalUser.id}" class="btn btn-primary">Update</a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/rental_user/delete/${rentalUser.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:mainPageTag>