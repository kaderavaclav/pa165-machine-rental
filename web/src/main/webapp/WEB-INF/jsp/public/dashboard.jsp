<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:mainPageTag>
<jsp:attribute name="body">
<div class="jumbotron">
    <h1>Welcome!</h1>
    <p>This is a Machine rental portal for our customers. You can view our machines, list your rentals and manage your profile information.</p>
    <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/public/machineList" role="button">View machines</a></p>
</div>

</jsp:attribute>
</my:mainPageTag>