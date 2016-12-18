<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:mainPageTag title="Machines" subtitle="List of all machines">
<jsp:attribute name="body">

   <table class="table table-striped table-hover col-md-6">
       <thead>
       <tr>
           <th class="col-md-1">ID</th>
           <th class="col-md-2">Name</th>
           <th>Description</th>
       </tr>
       </thead>
       <tbody>
       <c:forEach items="${machines}" var="machine">
                <tr>
                    <td>${machine.id}</td>
                    <td><c:out value="${machine.name}"/></td>
                    <td><c:out value="${machine.description}"/></td>
                </tr>
            </c:forEach>
       </tbody>
   </table>
</jsp:attribute>
</my:mainPageTag>