<%-- 
    Document   : view
    Created on : Dec 16, 2016, 4:04:15 PM
    Author     : Peter Benus
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Rental detail">
<jsp:attribute name="body">
    
    <div class="row">
        <div class="col-xs-6">
            <table>
                <tr>
                    <td><h3>Customer name:</h3></td>
                    <td><h3><c:out value="${rental.customer.name}"/></h3></td>
                </tr>
                <tr>
                    <td><h4>Machine name:</h4></td>
                    <td><h4><c:out value="${rental.machine.name}"/></h4></td>
                </tr>
                <tr>
                    <td><h4>Date from:</h4></td>
                    <td><h4><fmt:formatDate value="${rental.dateStart}" type="date" dateStyle="medium"/></h4></td>
                </tr>
                <tr>
                    <td><h4>Date to:</h4></td>
                    <td><h4><fmt:formatDate value="${rental.dateEnd}" type="date" dateStyle="medium"/></h4></td>
                </tr>
                <tr>
                    <td><h4>Date of creation:</h4></td>
                    <td><h4><fmt:formatDate value="${rental.dateCreated}" type="date" dateStyle="medium"/></h4></td>
                </tr>
                <tr>
                    <td><h4>Note:</h4></td>
                    <td><h4><c:out value="${rental.note}"/></h4></td>
                </tr>
                <tr>
                    <td><h4>Employee:</h4></td>
                    <td><h4><c:out value="${rental.employee.name}"/></h4></td>
                </tr>
            </table>
        </div>
    </div>
</jsp:attribute>
</my:pagetemplate>