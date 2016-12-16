<%-- 
    Document   : updateRental
    Created on : Dec 16, 2016, 5:17:06 PM
    Author     : Peter Benus
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Update rental">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/admin/rental/updating"
                   modelAttribute="rentalUpdate" cssClass="form-horizontal">

           <%-- <div class="form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label">Customer username</form:label>
                    <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>--%>
            <div class="form-group">
                <form:label path="id" cssClass="col-sm-2 control-label">Id</form:label>
                    <div class="col-sm-10">
                    <form:input path="id" readonly="true" cssClass="form-control"/>
                </div>
            </div>
            <div class="form-group ${machine_error?'has-error':''}">
                <form:label path="machine" cssClass="col-sm-2 control-label">Machine</form:label>
                    <div class="col-sm-10">
                    <form:input path="machine" cssClass="form-control"/>
                    <form:errors path="machine" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${note_error?'has-error':''}">
                <form:label path="note" cssClass="col-sm-2 control-label">Note</form:label>
                    <div class="col-sm-10">
                    <form:textarea cols="80" rows="20" path="note" cssClass="form-control"/>
                    <form:errors path="note" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${dateFrom_error?'has-error':''}">
                <form:label path="dateFrom" cssClass="col-sm-2 control-label">Date from (dd.mm.yyyy)</form:label>
                    <div class="col-sm-10">
                    <form:input path="dateFrom" cssClass="form-control"/>
                    <form:errors path="dateFrom" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${dateTo_error?'has-error':''}">
                <form:label path="dateTo" cssClass="col-sm-2 control-label">Date to (dd.mm.yyyy)</form:label>
                    <div class="col-sm-10">
                    <form:input path="dateTo" cssClass="form-control"/>
                    <form:errors path="dateTo" cssClass="help-block"/>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Update rental</button>
        </form:form>
    </jsp:attribute>
</my:mainPageTag>
