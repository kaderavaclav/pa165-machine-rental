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
            <div class="col-md-2">
                <label>Customer name:</label>
            </div>
            <div class="col-md-10">
                ${rental.customer.name}
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Machine name:</label>
            </div>
            <div class="col-md-10">
                ${rental.machine.name}
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Date start:</label>
            </div>
            <div class="col-md-10">
                <fmt:formatDate value="${rental.dateStart}" type="date" dateStyle="medium"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Date end:</label>
            </div>
            <div class="col-md-10">
                <fmt:formatDate value="${rental.dateEnd}" type="date" dateStyle="medium"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Date of creation:</label>
            </div>
            <div class="col-md-10">
                <fmt:formatDate value="${rental.dateCreated}" type="date" dateStyle="medium"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Note:</label>
            </div>
            <div class="col-md-10">
                ${rental.note}
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Employee:</label>
            </div>
            <div class="col-md-10">
                ${rental.employee.name}
            </div>
        </div>
    </jsp:attribute>
</my:mainPageTag>