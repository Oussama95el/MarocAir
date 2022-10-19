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
<div class="p-6 max-w-sm bg-white rounded-lg border border-gray-200 shadow-md dark:bg-gray-800 dark:border-gray-700">
   <form action="Reservation" method="POST">
       <a href="#">
           <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white"><%=vol.getAirline() %></h5>
       </a>
       <p class="mb-3 font-normal text-gray-700 dark:text-gray-400"><%=vol.getVilledepart() %></p>
       <p class="mb-3 font-normal text-gray-700 dark:text-gray-400"><%=vol.getVillearrive() %></p>
       <p class="mb-3 font-normal text-gray-700 dark:text-gray-400"><%=vol.getDatedepart() %></p>
       <h2 class="mb-3 font-normal text-gray-700 dark:text-gray-400"><%=vol.getDatearrive() %></h2>
       <h2 class="mb-3 font-normal text-gray-700 dark:text-gray-400"><%=vol.getPrixvol() %></h2>
       <input type="hidden" name="nbr_anfant" value=<%=data.get("nbr_enfant")%>>
       <input type="hidden" name="nbr_adulte" value=<%=data.get("nbr_adulte")%>>
       <input type="hidden" name="idvol" value=<%=vol.getIdvol()%>>
       <input type="hidden" name="id" value="1">
       <a href="#" class="inline-flex items-center py-2 px-3 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
          <input type="submit" name="submit" value="Reserver">
           <svg aria-hidden="true" class="ml-2 -mr-1 w-4 h-4" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
       </a>

   </form>
</div>
<%}%>
</body>
</html>
