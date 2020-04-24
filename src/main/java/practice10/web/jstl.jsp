<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <c:forEach var="j" begin="1" end="9">
        <tr>
            <c:forEach var="i" begin="1" end="9">
                <td>
                        ${i * j}
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>

</body>
</html>
