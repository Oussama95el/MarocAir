<%--
  Created by IntelliJ IDEA.
  User: abdessalm staili
  Date: 10/16/2022
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.maroc_air.Modelles.Vol" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HashMap<String, Object> data = (HashMap<String, Object>) request.getAttribute("data");
    Vol[] volsDisponible  = (Vol[]) data.get("vol");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<%for(Vol vol:volsDisponible){%>
<h1><%=vol.getVillearrive() %></h1>
<%}%>
</body>
</html>
