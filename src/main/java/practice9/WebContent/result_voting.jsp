<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<table>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
