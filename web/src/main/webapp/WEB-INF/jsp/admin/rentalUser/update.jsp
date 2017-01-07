<%-- 
    Author:  Josef Plch
    Created: 2016-12-17
    Version: 2016-12-17
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Update user">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/admin/rentalUser/updating" modelAttribute="rentalUserDto" cssClass="form-horizontal">
            <div class="form-group">
                <form:label path="id" cssClass="col-sm-1 control-label">ID</form:label>
                <div class="col-sm-10">
                    <form:input path="id" readonly="true" cssClass="form-control"/>
                </div>
            </div>
            <div class="form-group ${name_error ? 'has-error' : ''}">
                <form:label path="name" cssClass="col-sm-1 control-label">Name</form:label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${username_error ? 'has-error' : ''}">
                <form:label path="username" cssClass="col-sm-1 control-label">Username</form:label>
                <div class="col-sm-10">
                    <form:input path="username" cssClass="form-control"/>
                    <form:errors path="username" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${email_error ? 'has-error' : ''}">
                <form:label path="email" cssClass="col-sm-1 control-label">E-mail</form:label>
                <div class="col-sm-10">
                    <form:input path="email" cssClass="form-control"/>
                    <form:errors path="email" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${roles_error ? 'has-error' : ''}">
                <form:label path="roles" cssClass="col-sm-1 control-label">Role</form:label>
                <div class="col-sm-10">
                    <input type="checkbox" path="roles" label="customer" /> customer
                    <br/>
                    <input type="checkbox" path="roles" label="employee" /> employee
                    <form:errors path="roles" cssClass="help-block"/>
                </div>
            </div>
            <div class="row">
            <div class="col-md-3 col-md-offset-1">
                <button class="btn btn-success" type="submit">Update user</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/rentalUser/list">Cancel</a>
            </div>
        </form:form>
    </jsp:attribute>
</my:mainPageTag>
