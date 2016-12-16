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

<my:pagetemplate title="Rentals">
<jsp:attribute name="body">

    <a href="${pageContext.request.contextPath}/admin/rental/newRental" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New rental
    </a>
    
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>customer</th>
            <th>machine</th>
            <th>start date</th>
            <th>end date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rentals}" var="rental">
            <tr>
                <td>${rental.id}</td>
                <td><c:out value="${rental.customer.name}"/></td>
                <td><c:out value="${rental.machine.name}"/></td>
                <td><fmt:formatDate value="${rental.dateStart}" type="date" dateStyle="medium"/></td>
                <td><fmt:formatDate value="${rental.dateEnd}" type="date" dateStyle="medium"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/rental/view/${rental.id}" class="btn btn-primary">Detail</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/rental/updateRental/${rental.id}" class="btn btn-primary">Update</a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/rental/deleteRental/${rental.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
