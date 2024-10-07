<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Administrera Kategorier</title>
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
<h1>Hantera Kategorier</h1>

<!-- Table for displaying categories -->
<table>
  <thead>
  <tr>
    <th>Kategori Namn</th>
    <th>Åtgärder</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="category" items="${categoryList}">
    <tr>
      <td>${category.name}</td>
      <td>
        <form action="${pageContext.request.contextPath}/admin/category/edit" method="get" style="display:inline;">
          <input type="hidden" name="categoryId" value="${category.id}" />
          <button type="submit" class="button">Redigera</button>
        </form>
        <form action="${pageContext.request.contextPath}/admin/category/delete" method="post" style="display:inline;">
          <input type="hidden" name="categoryId" value="${category.id}" />
          <button type="submit" class="button" style="background-color: #f44336;">Ta bort</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<!-- Form for adding a new category -->
<h3>Lägg till ny kategori</h3>
<form action="${pageContext.request.contextPath}/admin/category/add" method="post">
  <label for="categoryName">Kategori Namn:</label><br>
  <input type="text" id="categoryName" name="categoryName" required><br><br>

  <button type="submit" class="button">Lägg till Kategori</button>
</form>

</body>
</html>
