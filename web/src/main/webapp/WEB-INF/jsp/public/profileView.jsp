<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Your profile" subtitle="${authUser.getName()}">
<jsp:attribute name="body">
    <div class="row">
        <div class="col-md-1">
            <label>Username:</label>
        </div>
        <div class="col-md-11">
                ${authUser.getUsername()}
        </div>
    </div>
    <div class="row">
        <div class="col-md-1">
            <label>Name:</label>
        </div>
        <div class="col-md-11">
                ${authUser.getName()}
        </div>
    </div>
    <div class="row">
        <div class="col-md-1">
            <label>Email:</label>
        </div>
        <div class="col-md-11">
                ${authUser.getEmail()}
        </div>
    </div>
    <div class="row">
        <div class="col-md-1">
            <label>Roles:</label>
        </div>
        <div class="col-md-11">
                ${authUser.getRoles()}
        </div>
    </div>
    <div class="row" style="margin-bottom:50px;">
        <div class="col-md-1">
            <label>Legal personality:</label>
        </div>
        <div class="col-md-11">
                ${authUser.getLegalPersonality()}
        </div>
    </div>

    <div class="row" style="margin-bottom:50px;">
        <div class="col-md-1">
            <a class="btn btn-default" href="${pageContext.request.contextPath}/public/updateProfile">Update profile information</a>
        </div>
    </div>
</jsp:attribute>
</my:mainPageTag>