<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Administrera Användare</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Hantera Användare</h1>

<!-- Table for displaying users -->
<table>
    <thead>
    <tr>
        <th>Användarnamn</th>
        <th>Lösenord</th>
        <th>Roll</th>
        <th>Åtgärder</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/user/edit" method="get" style="display:inline;">
                    <input type="hidden" name="userId" value="${user.id}" />
                    <button type="submit" class="button">Redigera</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/user/delete" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="${user.id}" />
                    <button type="submit" class="button" style="background-color: #f44336;">Ta bort</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Form for adding a new user -->
<h3>Lägg till ny användare</h3>
<form action="${pageContext.request.contextPath}/admin/user/add" method="post">
    <label for="username">Användarnamn:</label><br>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <label for="role">Roll:</label><br>
    <select id="role" name="role" required>
        <option value="admin">Admin</option>
        <option value="user">Användare</option>
    </select><br><br>

    <button type="submit" class="button">Lägg till Användare</button>
</form>

</body>
</html>
