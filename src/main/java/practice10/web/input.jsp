<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="input" method="post">
    <input type="text" name="input">
    <button type="submit">Add</button>
    <button type="submit" name="clear" value="clear">Remove</button>
</form>
<c:forEach var="value" items="${listValues}">
    ${value}<br>
</c:forEach>
</body>
</html>
