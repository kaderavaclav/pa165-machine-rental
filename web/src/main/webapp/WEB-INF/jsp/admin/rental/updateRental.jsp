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

<my:mainPageTag title="Rentals > Update rental">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/admin/rental/updatingRental"
                   modelAttribute="updateRental" cssClass="form-horizontal">
            <div class="form-group ${id_error?'has-error':''}">
                <form:label path="id" cssClass="col-sm-2 control-label">Rental ID</form:label>
                    <div class="col-sm-10">
                    <form:input path="id" readonly="true" cssClass="form-control"/>
                    <form:errors path="id" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${customerId_error?'has-error':''}">
                <form:label path="customerId" cssClass="col-sm-2 control-label">Customer</form:label>
                    <div class="col-sm-10">
                    <form:select path="customerId" cssClass="form-control" id="customerId">
                        <form:options items="${customerList}"/>
                    </form:select>
                </div>
            </div>
            <div class="form-group ${machineId_error?'has-error':''}">
                <form:label path="machineId" cssClass="col-sm-2 control-label">Machine</form:label>
                    <div class="col-sm-10">
                    <form:select path="machineId" cssClass="form-control" id="machineId">
                        <form:options items="${machineList}"/>
                    </form:select>
                </div>
            </div>
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
            <button class="btn btn-primary" type="submit">Update rental</button>
        </form:form>
    </jsp:attribute>
</my:mainPageTag>
