<%--
  Created by IntelliJ IDEA.
  User: vitaliilatysh
  Date: 2018-10-22
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vote</title>
</head>
<body>
<form method="get" action="resultVote">
    <select name="vote">
        <option value="Biathlon">Biathlon</option>
        <option value="Basketball">Basketball</option>
        <option value="Football">Football</option>
    </select>
    <br>
    <input type="submit" value="Vote">
</form>
</body>
</html>
