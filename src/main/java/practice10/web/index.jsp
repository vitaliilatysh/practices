<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Practice10</title>
</head>
<body>
<table border="1">
    <% for(int j = 1; j<=9; j++) {%>
    <tr>
        <%
            for (int i = 1; i <= 9; i++) {%>
        <td>
            <%=i * j%>
        </td>
        <% } %>
    </tr>
   <%}%>
</table>
</body>
</html>
