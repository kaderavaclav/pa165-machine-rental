<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Machines" subtitle="View detail">
<jsp:attribute name="body">
    <div class="row">
        <div class="col-md-1">
            <label>Name:</label>
        </div>
        <div class="col-md-11">
                ${machine.name}
        </div>
    </div>
    <div class="row" style="margin-bottom:50px;">
        <div class="col-md-1">
            <label>Description:</label>
        </div>
        <div class="col-md-11">
                ${machine.description}
        </div>
    </div>
    <h3>Machine revisions</h3>
    <table class="table table-striped table-bordered table-hover col-md-12">
        <thead>
            <th class="col-md-1">ID</th>
            <th class="col-md-2">Date</th>
            <th>Note</th>
            <th class="col-md-1">Actions</th>
        </thead>
        <tbody>
        <c:forEach items="${revisions}" var="revision">
            <tr>
                <td>${revision.id}</td>
                <td><fmt:formatDate value="${revision.revisionDate}" type="date" dateStyle="medium"/></td>
                <td><c:out value="${revision.note}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/revision/view/${revision.id}" class="btn btn-default">View</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:mainPageTag>