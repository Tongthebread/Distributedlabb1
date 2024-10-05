<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hantera Produkter</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<h1>Hantera Produkter</h1>
<!-- Formulär för att lägga till ny produkt -->
<h2>Lägg till Ny Produkt</h2>
<form action="${pageContext.request.contextPath}/admin" method="post">
    <input type="hidden" name="action" value="add">
    <label for="name">Produktnamn:</label><br>
    <input type="text" id="name" name="name" required><br><br>
    <label for="price">Pris:</label><br>
    <input type="number" id="price" name="price" step="0.01" required><br><br>
    <label for="stock">Lagersaldo:</label><br>
    <input type="number" id="stock" name="stock" required><br><br>
    <label for="categoryId">Kategori ID:</label><br>
    <input type="number" id="categoryId" name="categoryId" required><br><br>
    <!-- Lägg till andra fält som beskrivning, bild-URL, etc. -->
    <button type="submit">Lägg till Produkt</button>
</form>
<hr>
<!-- Lista över befintliga produkter -->
<h2>Befintliga Produkter</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Namn</th>
        <th>Pris</th>
        <th>Lagersaldo</th>
        <th>Kategori ID</th>
        <th>Åtgärder</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price} SEK</td>
            <td>${product.stock}</td>
            <td>${product.categoryId}</td>
            <td>
                <!-- Formulär för att uppdatera produkt -->
                <form action="${pageContext.request.contextPath}/admin" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="productId" value="${product.id}">
                    <!-- Lägg till fält för uppdatering om nödvändigt -->
                    <button type="submit">Redigera</button>
                </form>
                <!-- Formulär för att ta bort produkt -->
                <form action="${pageContext.request.contextPath}/admin" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="productId" value="${product.id}">
                    <button type="submit" onclick="return confirm('Är du säker på att du vill ta bort denna produkt?');">Ta bort</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

