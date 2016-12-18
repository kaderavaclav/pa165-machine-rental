<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:mainPageTag title="Rentals" subtitle="List of all your rentals">
<jsp:attribute name="body">
   <table class="table table-striped table-hover col-md-6">
       <thead>
       <tr>
           <th class="col-md-1">ID</th>
           <th class="col-md-3">Machine</th>
           <th class="col-md-1">Start date</th>
           <th class="col-md-1">End date</th>
       </tr>
       </thead>
       <tbody>
       <c:forEach items="${rentals}" var="rental">
            <tr>
                <td>${rental.id}</td>
                <td><c:out value="${rental.machine.name}"/></td>
                <td><fmt:formatDate value="${rental.dateStart}" type="date" dateStyle="medium"/></td>
                <td><fmt:formatDate value="${rental.dateEnd}" type="date" dateStyle="medium"/></td>
            </tr>
        </c:forEach>
       </tbody>
   </table>
</jsp:attribute>
</my:mainPageTag>