<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>${product.name}</title>
</head>
<body>
<h1>${product.name}</h1>
<p>Pris: ${product.price} SEK</p>
<p>Lagersaldo: ${product.stock}</p>
<p>Beskrivning: ${product.description}</p>
<!-- Bild om tillgänglig -->
<c:if test="${not empty product.imageUrl}">
  <img src="${product.imageUrl}" alt="${product.name}" width="300" height="300">
</c:if>
<form action="${pageContext.request.contextPath}/cart" method="post">
  <input type="hidden" name="productId" value="${product.id}" />
  <input type="hidden" name="action" value="add" />
  <label for="quantity">Antal:</label>
  <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.stock}" />
  <button type="submit">Lägg till i varukorg</button>
</form>
<p><a href="${pageContext.request.contextPath}/products">Tillbaka till produkter</a></p>
</body>
</html>

