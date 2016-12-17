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
        <div class="col-xs-6">
            <table>
                <tr>
                    <td><h4>ID:</h4></td>
                    <td><c:out value="${rentalUserDto.id}"/></td>
                </tr>
                <tr>
                    <td><h4>Name:</h4></td>
                    <td><c:out value="${rentalUserDto.name}"/></td>
                </tr>
                <tr>
                    <td><h4>Username</h4></td>
                    <td><c:out value="${rentalUserDto.username}"/></td>
                </tr>
                <tr>
                    <td><h4>E-mail:</h4></td>
                    <td><c:out value="${rentalUserDto.email}"/></td>
                </tr>
                <tr>
                    <td><h4>Roles:</h4></td>
                    <td><c:out value="${rentalUserDto.roles}"/></td>
                    <%--  <td><c:forEach items="${rentalUserDto.roles}" var="role"><c:out value="${role}"/></c:forEach></td> --%>
                </tr>
                <tr>
                    <td><h4>Legal personality:</h4></td>
                    <td><c:out value="${rentalUserDto.legalPersonality}"/></td>
                </tr>
            </table>
        </div>
    </div>
</jsp:attribute>
</my:mainPageTag>