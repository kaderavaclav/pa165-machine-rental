<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:mainPageTag title="Machines" subtitle="List">
<jsp:attribute name="body">
    <div class="row" style="margin-bottom: 50px;">
        <div class="col-md-2">
            <a href="${pageContext.request.contextPath}/admin/machine/new" class="btn btn-success">Create machine</a>
        </div>
    </div>

<div class="row">
    <div class="col-md-12">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th class="col-md-1">ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th colspan="3" class="col-md-3">Actions</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${machines}" var="machine">
                <tr>
                    <td>${machine.id}</td>
                    <td><c:out value="${machine.name}"/></td>
                    <td><c:out value="${machine.description}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/machine/view/${machine.id}" class="btn btn-default">Detail</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/machine/update/${machine.id}" class="btn btn-default">Edit</a>
                    </td>

                    <td>
                        <form method="post"
                              action="${pageContext.request.contextPath}/admin/machine/delete/${machine.id}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</jsp:attribute>
</my:mainPageTag>