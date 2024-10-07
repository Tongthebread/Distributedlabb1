<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Varukorg</title>
</head>
<body>
<h1>Din Varukorg</h1>
<c:choose>
    <c:when test="${empty sessionScope.cart || sessionScope.cart.items.size() == 0}">
        <p>Din varukorg är tom.</p>
        <p><a href="${pageContext.request.contextPath}/products">Fortsätt handla</a></p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <th>Produkt</th>
                <th>Pris</th>
                <th>Kvantitet</th>
                <th>Totalt</th>
                <th>Åtgärder</th>
            </tr>
            <c:forEach var="item" items="${sessionScope.cart.items.values()}">
                <tr>
                    <td>${item.product.name}</td>
                    <td>${item.product.price} SEK</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/cart" method="post">
                            <input type="hidden" name="action" value="update" />
                            <input type="hidden" name="productId" value="${item.product.id}" />
                            <input type="number" name="quantity" value="${item.quantity}" min="1" max="${item.product.stock}" />
                            <button type="submit">Uppdatera</button>
                        </form>
                    </td>
                    <td>${item.product.price * item.quantity} SEK</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/cart" method="post">
                            <input type="hidden" name="action" value="remove" />
                            <input type="hidden" name="productId" value="${item.product.id}" />
                            <button type="submit">Ta bort</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><strong>Totalpris:</strong> ${sessionScope.cart.totalPrice} SEK</p>
        <a href="${pageContext.request.contextPath}/checkout">Till kassan</a>
    </c:otherwise>
</c:choose>
</body>
</html>

