<%-- 
    Document   : newRental
    Created on : Dec 16, 2016, 4:12:57 PM
    Author     : Peter Benus
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Rentals > Create new">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/admin/rental/createRental"
                   modelAttribute="newRental" cssClass="form-vertical">
            <div class="form-group ${note_error?'has-error':''}">
                <form:label path="note" cssClass="col-sm-2 control-label">Note</form:label>
                    <div class="col-sm-10">
                    <form:input path="note" cssClass="form-control"/>
                    <form:errors path="note" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${dateStart_error?'has-error':''}">
                <form:label path="dateStart" cssClass="col-sm-2 control-label">Date from (dd.mm.yyyy)</form:label>
                    <div class="col-sm-10">
                    <form:input path="dateStart" cssClass="form-control"/>
                    <form:errors path="dateStart" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${dateEnd_error?'has-error':''}">
                <form:label path="dateEnd" cssClass="col-sm-2 control-label">Date to (dd.mm.yyyy)</form:label>
                    <div class="col-sm-10">
                    <form:input path="dateEnd" cssClass="form-control"/>
                    <form:errors path="dateEnd" cssClass="help-block"/>
                </div>
            </div>
                <div class="col-sm-3">
            <button class="btn btn-primary" type="submit">Create rental</button>
            </div>
        </form:form>
    </jsp:attribute>
</my:mainPageTag>