<%-- 
    Document   : list
    Created on : Dec 16, 2016, 3:43:32 PM
    Author     : Peter Benus
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Rentals">
<jsp:attribute name="body">

    <div class="row" style="margin-bottom: 50px;">
        <div class="col-md-2">
            <a href="/pa165/admin/rental/newRental" class="btn btn-success">New rental</a>
        </div>
    </div>
    
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th class="col-md-1">ID</th>
            <th>Customer</th>
            <th>Machine</th>
            <th>Start date</th>
            <th>End date</th>
            <th colspan="3" class="col-md-3">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rental}" var="rental">
            <tr>
                <td>${rental.id}</td>
                <td><c:out value="${rental.customer.name}"/></td>
                <td><c:out value="${rental.machine.name}"/></td>
                <td><fmt:formatDate value="${rental.dateStart}" type="date" dateStyle="medium"/></td>
                <td><fmt:formatDate value="${rental.dateEnd}" type="date" dateStyle="medium"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/rental/view/${rental.id}" class="btn btn-default">Detail</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/rental/updateRental/${rental.id}" class="btn btn-default">Update</a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/rental/deleteRental/${rental.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:mainPageTag>
