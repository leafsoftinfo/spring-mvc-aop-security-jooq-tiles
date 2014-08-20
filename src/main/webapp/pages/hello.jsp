<%@page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Message : ${message}</h1>	
<ul>
    <li><a href="<c:url value="/register" />">Register</a></li>
    <li><a href="<c:url value="/login" />">Login</a></li>
</ul>
