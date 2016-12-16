<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:mainPageTag title="Machines">
<jsp:attribute name="body">
   <a href="/pa165/admin/machine/new" class="btn btn-default">Create machine</a>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th colspan="2">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${machines}" var="machine">
            <tr>
                <td>${machine.id}</td>
                <td><c:out value="${machine.name}"/></td>
                <td><c:out value="${machine.description}"/></td>
                <td>
                    <a href="/pa165/admin/machine/update/${machine.id}" class="btn btn-default">Edit</a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/machine/delete/${machine.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:mainPageTag>