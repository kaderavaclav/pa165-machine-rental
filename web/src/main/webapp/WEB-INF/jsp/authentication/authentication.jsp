<%-- 
    Document   : authentication
    Created on : Dec 15, 2016, 7:36:26 PM
    Author     : Peter Benus
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:mainPageTag title="Sign in">
<jsp:attribute name="body">


        <form method="POST" action="/pa165/authentication/authentication">
            <div class="form-group">
                <label for="usernameField" class="control-label">Username:</label>
                    <input type="text" id="usernameField" name="username" cssClass="form-control" placeholder="username..."/>
            </div>
            <div class="form-group">
                <label for="passwordField" class="control-label">Password:</label>
                    <input id="passwordField" name="password" cssClass="form-control" type="password" placeholder="password..."/>
            </div>
            <button class="btn btn-primary" type="submit">Sign in</button>
        </form>

</jsp:attribute>
</my:mainPageTag>
