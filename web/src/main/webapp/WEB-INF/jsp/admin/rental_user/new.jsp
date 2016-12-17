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

<my:mainPageTag title="New user">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/admin/rental_user/create" modelAttribute="rentalUserDto" cssClass="form-horizontal">
            <div class="form-group ${name_error ? 'has-error' : ''}">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${username_error ? 'has-error' : ''}">
                <form:label path="username" cssClass="col-sm-2 control-label">Note</form:label>
                <div class="col-sm-10">
                    <form:input path="username" cssClass="form-control"/>
                    <form:errors path="username" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${email_error ? 'has-error' : ''}">
                <form:label path="email" cssClass="col-sm-2 control-label">Note</form:label>
                <div class="col-sm-10">
                    <form:input path="email" cssClass="form-control"/>
                    <form:errors path="email" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${roles_error ? 'has-error' : ''}">
                <form:label path="roles" cssClass="col-sm-2 control-label">Note</form:label>
                <div class="col-sm-10">
                    <form:checkbox path="customer" value="customer" label="customer" /> customer
                    <br/>
                    <form:checkbox path="employee" value="employee" label="employee" /> employee
                    <form:errors path="roles" cssClass="help-block"/>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Create user</button>
        </form:form>
    </jsp:attribute>
</my:mainPageTag>
