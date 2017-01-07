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

<my:mainPageTag title="User profile">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-md-1">
                <label>ID:</label>
            </div>
            <div class="col-md-11">
                ${rentalUserDto.id}
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <label>Name:</label>
            </div>
            <div class="col-md-11">
                ${rentalUserDto.name}
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <label>Username:</label>
            </div>
            <div class="col-md-11">
                ${rentalUserDto.username}
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <label>E-mail:</label>
            </div>
            <div class="col-md-11">
                ${rentalUserDto.email}
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <label>Roles:</label>
            </div>
            <div class="col-md-11">
                ${rentalUserDto.roles}
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <label>Legal personality:</label>
            </div>
            <div class="col-md-11">
                ${rentalUserDto.legalPersonality}
            </div>
        </div>
    </jsp:attribute>
</my:mainPageTag>