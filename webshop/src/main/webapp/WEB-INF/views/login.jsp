<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="${pageContext.request.contextPath}/auth" method="post">
  <label for="username">Username:</label><br>
  <input type="text" id="username" name="username" required><br><br>
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password" required><br><br>
  <button type="submit">Log in</button>
</form>
<p>No acccount? <a href="${pageContext.request.contextPath}/register">Register:</a>.</p>
<c:if test="${not empty errorMessage}">
  <p style="color:red;">${errorMessage}</p>
</c:if>
<c:if test="${not empty param.message}">
  <p style="color:green;">${param.message}</p>
</c:if>
</body>
</html>

