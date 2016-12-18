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
        <div class="col-md-1">
            <label>Machine:</label>
        </div>
        <div class="col-md-11">
                ${revision.machine.name}
        </div>
    </div>
    <div class="row">
        <div class="col-md-1">
            <label>RevisionDate:</label>
        </div>
        <div class="col-md-11">
            <fmt:formatDate value="${revision.revisionDate}" type="date" dateStyle="medium"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-1">
            <label>Mechanic:</label>
        </div>
        <div class="col-md-11">
                ${revision.mechanic.name}
        </div>
    </div>
    <div class="row" style="margin-bottom:50px;">
        <div class="col-md-1">
            <label>Note:</label>
        </div>
        <div class="col-md-11">
                ${revision.note}
        </div>
    </div>

</jsp:attribute>
</my:mainPageTag>