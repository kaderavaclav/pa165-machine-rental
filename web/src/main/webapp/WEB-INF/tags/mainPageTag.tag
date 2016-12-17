<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="subtitle" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}"/></title>
    <!-- bootstrap loaded from content delivery network -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
          crossorigin="anonymous">
    <jsp:invoke fragment="head"/>
</head>
<body>
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Machine rental portal</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <c:if test="${!authUser.getIsAdmin()}">
                    <li>
                        <a href="${pageContext.request.contextPath}/public/machineList">All machines</a>
                    </li>
                </c:if>

                <c:if test="${authUser.getIsAdmin()}">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/machine/list">Machine</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/revision/list">Revisions</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/rentalUser/list">Users</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/rental/list">Rentals</a>
                    </li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty authUser}">
                    <li>
                        <p class="navbar-text">Signed in as
                                ${authUser.getUsername()}</p>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/authentication/logout">Logout</a>
                    </li>
                </c:if>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <!-- page title -->

    <div class="page-header row">
        <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10">
            <c:if test="${not empty title}">
                <h1><c:out value="${title}"/>
                    &nbsp;
                    <small><c:out value="${subtitle}" /></small>
                </h1>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">

            <!-- alerts -->
            <c:if test="${not empty alert_danger}">
                <div class="alert alert-danger fade in" role="alert">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <c:out value="${alert_danger}"/></div>
            </c:if>
            <c:if test="${not empty alert_info}">
                <div class="alert alert-info fade in" role="alert">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:out value="${alert_info}"/>
                </div>
            </c:if>
            <c:if test="${not empty alert_success}">
                <div class="alert alert-success fade in" role="alert">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:out value="${alert_success}"/>
                </div>
            </c:if>
            <c:if test="${not empty alert_warning}">
                <div class="alert alert-warning fade in" role="alert">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:out value="${alert_warning}"/>
                </div>
            </c:if>
        </div>
    </div>
    <!-- page body -->
    <div class="row">
        <div class="col-md-12">
            <jsp:invoke fragment="body"/>
        </div>
    </div>
    <!-- footer -->
    <div class="row">
        <div class="col-lg-12">
            <footer class="footer">
                <hr/>
                <div class="container">
                    <p class="text-muted">&copy; PA165 Machine Rental</p>
                    <li>Václav Kaděra, Peter Beňuš, Zuzana Schwarzová, Josef Plch</li>
                </div>
            </footer>
        </div>
    </div>
</div>
</div>
<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>