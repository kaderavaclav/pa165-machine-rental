<%--
  Created by IntelliJ IDEA.
  User: zuz-schwarzova
  Date: 15. 12. 2016
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Revision detail">
<jsp:attribute name="body">

    <div class="row">
        <div class="col-xs-6">
            <table>
                <tr>
                    <td><h3>Machine:</h3></td>
                    <td><h3><c:out value="${revision.machine}"/></h3></td>
                </tr>
                <tr>
                    <td><h4>Mechanic:</h4></td>
                    <td><h4><c:out value="${revision.mechanic}"/></h4></td>
                </tr>
                <tr>
                    <td><h4>Revision date:</h4></td>
                    <td><h4><fmt:formatDate value="${revision.revisionDate}" type="date" dateStyle="medium"/></h4></td>
                </tr>
                <tr>
                    <td><h4>Note:</h4></td>
                    <td><h4><c:out value="${revision.note}"/></h4></td>
                </tr>
            </table>
        </div>
    </div>
</jsp:attribute>
</my:mainPageTag>