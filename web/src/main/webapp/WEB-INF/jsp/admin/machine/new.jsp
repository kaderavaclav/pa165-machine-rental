<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Machines" subtitle="Create new">
<jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/admin/machine/create"
               modelAttribute="newMachine" cssClass="form-vertical">
       <div class="row" style="margin-bottom: 10px;">
           <div class="form-group ${name_error?'has-error':''}">
               <form:label path="name" cssClass="col-sm-1 control-label">Name</form:label>
               <div class="col-md-5">
                   <form:input path="name" cssClass="form-control"/>
                   <form:errors path="name" cssClass="help-block"/>
               </div>
           </div>
       </div>
        <div class="row" style="margin-bottom: 10px;">
            <div class="form-group ${description_error?'has-error':''}">
                <form:label path="description" cssClass="col-sm-1 control-label">Description</form:label>
                <div class="col-md-5">
                    <form:input path="description" cssClass="form-control"/>
                    <form:errors path="description" cssClass="help-block"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 col-md-offset-1">
                <button class="btn btn-success" type="submit">Create machine</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/machine/list">Cancel</a>
            </div>
        </div>
    </form:form>
</jsp:attribute>
</my:mainPageTag>