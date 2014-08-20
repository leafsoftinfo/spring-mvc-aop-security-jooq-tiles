<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Message : ${message}</h1>

<c:if test="${username != null}">
    <h2>Welcome : ${username}</h2>
    <p>
        <a href="<c:url value="/admin/ok" />">Authorized page</a>
    </p>
    <p>
        <a href="<c:url value="/admin/ko" />">Unauthorized page</a>
    </p>

    <c:url value="/j_spring_security_logout" var="logoutUrl" />


    <!-- csrt for log out-->
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" 
               name="${_csrf.parameterName}"
               value="${_csrf.token}" />
        <input type="submit" value="logout" />
    </form>
</c:if>
