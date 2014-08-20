<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="login-box">

    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form:form name="loginForm" method='POST' commandName="formObject">
        <table>
            <tr>    
                <td><form:label path="username">User:</form:label></td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td><form:label path="password">Password:</form:label></td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form:form>
</div>