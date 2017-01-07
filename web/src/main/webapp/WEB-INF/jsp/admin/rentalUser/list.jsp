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
        <div class="row" style="margin-bottom: 50px;">
            <div class="col-md-2">
                <a href="/pa165/admin/rentalUser/new" class="btn btn-success">New user</a>
            </div>
        </div>

        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th class="col-md-1">ID</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>E-mail</th>
                    <th colspan="3" class="col-md-3">Actions</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${rentalUsers}" var="rentalUser">
                    <tr>
                        <td>${rentalUser.id}</td>
                        <td><c:out value="${rentalUser.name}"/></td>
                        <td><c:out value="${rentalUser.username}"/></td>
                        <td><c:out value="${rentalUser.email}"/></td>
                        <td>
                            <a href="/pa165/admin/rentalUser/view/${rentalUser.id}" class="btn btn-default">Detail</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/rentalUser/update/${rentalUser.id}" class="btn btn-default">Update</a>
                        </td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/admin/rentalUser/delete/${rentalUser.id}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:mainPageTag>