
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Produkter</title>
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
        .add-to-cart-button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .add-to-cart-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Produkter</h1>

<table>
    <thead>
    <tr>
        <th>Produktnamn</th>
        <th>Pris</th>
        <th>Lagersaldo</th>
        <th>Åtgärder</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
        <tr>
            <!-- Produktnamn with link to details page -->
            <td><a href="${pageContext.request.contextPath}/products?id=${product.id}">${product.name}</a></td>

            <!-- Pris -->
            <td>${product.price} SEK</td>

            <!-- Lagersaldo -->
            <td>${product.stock}</td>

            <!-- Lägg till i varukorg button -->
            <td>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="productId" value="${product.id}" />
                    <input type="hidden" name="action" value="add" />
                    <button type="submit" class="add-to-cart-button">Lägg till i varukorg</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>