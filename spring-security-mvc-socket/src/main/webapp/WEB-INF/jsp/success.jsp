<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html ng-app='angularApp'>
<head>
    <title>Spring Secured Sockets</title>
    <link href="<c:url value="/resources/styles/style.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/vendor/angular/angular.min.js" />"></script>
    <script src="<c:url value="/resources/vendor/angular/angular-route.min.js" />"></script>

    <script src="<c:url value="/resources/scripts/app.js" />"></script>
    <script src="<c:url value="/resources/scripts/services/SocketService.js" />"></script>
    <script src="<c:url value="/resources/scripts/factories/Form.js" />"></script>
    <script src="<c:url value="/resources/scripts/factories/PetFactory.js" />"></script>
    <script src="<c:url value="/resources/scripts/controllers/indexController.js" />"></script>
    <script src="<c:url value="/resources/scripts/controllers/loginController.js" />"></script>
    <script src="<c:url value="/resources/scripts/controllers/socketController.js" />"></script>
    <script src="<c:url value="/resources/scripts/controllers/successController.js" />"></script>
    <script src="<c:url value="/resources/scripts/routes/router.js" />"></script>
</head>
<body ng-controller="successController">
    <h1>Congrats! You've logged in.</h1>

    <h2>Your pets:</h2>
    ${Pets}

    <div ng-repeat="pet in Pets">
        <h1>All Pets</h1>
        <td>{{ pet.name }}</td>
        <td>{{ pet.type }}</td>
    </div>

    <a href="${pageContext.request.contextPath}/secured/socket">Click to chat!</a>
    <a href="${pageContext.request.contextPath}/">Click to start over (you will still be authenticated)!</a>

    <!-- CSRF Token -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</body>
</html>