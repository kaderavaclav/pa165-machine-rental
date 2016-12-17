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

<my:mainPageTag title="New revision">
<jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/admin/revision/create"
               modelAttribute="revisionCreate" cssClass="form-horizontal">
        <div class="form-group ${machine_error?'has-error':''}">
            <form:label path="machine" cssClass="col-sm-2 control-label">machine</form:label>
            <div class="col-sm-10">
                 <form:select path="machine" cssClass="form-control" id="machine">
                     <form:options items="${machineList}"/>
                 </form:select>
            </div>
        </div>
        <div class="form-group ${mechanic_error?'has-error':''}">
            <form:label path="mechanic" cssClass="col-sm-2 control-label">mechanic</form:label>
            <div class="col-sm-10">
                 <form:select path="machine" cssClass="form-control" id="rentalUser">
                     <form:options items="${userList}"/>
                 </form:select>
            </div>
        </div>
        <div class="form-group ${note_error?'has-error':''}">
            <form:label path="note" cssClass="col-sm-2 control-label">note</form:label>
            <div class="col-sm-10">
                <form:textarea cols="80" rows="20" path="note" cssClass="form-control"/>
                <form:errors path="note" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${revisionDate_error?'has-error':''}">
            <form:label path="revisionDate" cssClass="col-sm-2 control-label">revisionDate (yyyy.mm.dd)</form:label>
            <div class="col-sm-10">
                <form:input path="revisionDate" cssClass="form-control"/>
                <form:errors path="revisionDate" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Create revision</button>
    </form:form>


</jsp:attribute>
</my:mainPageTag>