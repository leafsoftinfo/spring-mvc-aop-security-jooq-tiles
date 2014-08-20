<%-- 
    Document   : layout
    Created on : 19 août 2014, 13:54:39
    Author     : cwirth
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><tiles:insertAttribute name="title" /></title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
    </head>
    <body>
        <tiles:insertAttribute name="header"  />
        
        <div class="content">
            <tiles:insertAttribute name="content" />
        </div>
        
        <tiles:insertAttribute name="footer" />
    </body>
</html>
