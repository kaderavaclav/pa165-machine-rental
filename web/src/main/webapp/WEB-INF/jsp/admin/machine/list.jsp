<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:mainPageTag title="Machines">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>machine desc</th>
            <th>isAvailable</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>value</td>
            <td>value</td>
            <td>value</td>
            <td>value</td>
            <td>action buttons</td>
        </tr>
        </tbody>
    </table>
</jsp:attribute>
</my:mainPageTag>