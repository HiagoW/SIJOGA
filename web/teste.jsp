<%-- 
    Document   : teste
    Created on : 07/03/2020, 10:42:11
    Author     : hiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            ArrayList<Integer> arr = new ArrayList<Integer>();
            arr.add(10);
            arr.add(20);
            arr.add(30);
            for(Integer i: arr)
                out.println("<h2>"+i+"</h2>");
        %>
    </body>
</html>
