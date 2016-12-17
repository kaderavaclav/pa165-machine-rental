<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Machines" subtitle="View detail">
<jsp:attribute name="body">
    <table>
        <tr>
            <td>
                Name:
            </td>
            <td>
                ${machine.name}
            </td>
        </tr>
        <tr>
            <td>
                Description:
            </td>
            <td>
                ${machine.description}
            </td>
        </tr>
    </table>
    <h3>Machine revisions:</h3>
    <table>
        <thead>
            <th>ID</th>
            <th>Date</th>
            <th>Note</th>
            <th>Actions</th>
        </thead>
        <tbody>
        <c:forEach items="${revisions}" var="revision">
            <tr>
                <td>${revision.id}</td>
                <td><c:out value="${revision.revisionDate}"/></td>
                <td><c:out value="${revision.note}"/></td>
                <td>
                    <a href="/pa165/admin/revision/view/${machine.id}" class="btn btn-default">View</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:mainPageTag>