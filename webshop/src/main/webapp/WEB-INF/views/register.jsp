<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Registrering</title>
</head>
<body>
<h1>Registrera dig</h1>
<form action="${pageContext.request.contextPath}/register" method="post">
    <label for="username">Användarnamn:</label><br>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">Lösenord:</label><br>
    <input type="password" id="password" name="password" required><br><br>
    <label for="confirmPassword">Bekräfta Lösenord:</label><br>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
    <button type="submit">Registrera</button>
</form>
<p>Har du redan ett konto? <a href="${pageContext.request.contextPath}/login.jsp">Logga in här</a>.</p>
<c:if test="${not empty errorMessage}">
    <p style="color:red;">${errorMessage}</p>
</c:if>
</body>
</html>