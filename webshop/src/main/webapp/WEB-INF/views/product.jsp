<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Produkter</title>
</head>
<body>
<h1>Produkter</h1>
<c:forEach var="product" items="${products}">
    <div class="product-item">
        <h2><a href="${pageContext.request.contextPath}/productDetails?id=${product.id}">${product.name}</a></h2>
        <p>Pris: ${product.price} SEK</p>
        <p>Lagersaldo: ${product.stock}</p>
        <!-- Bild om tillgänglig -->
        <c:if test="${not empty product.imageUrl}">
            <img src="${product.imageUrl}" alt="${product.name}" width="150" height="150">
        </c:if>
        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="productId" value="${product.id}" />
            <input type="hidden" name="action" value="add" />
            <button type="submit">Lägg till i varukorg</button>
        </form>
    </div>
    <hr>
</c:forEach>
</body>
</html>

