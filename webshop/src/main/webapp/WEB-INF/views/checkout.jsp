<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Kassa</title>
</head>
<body>
<h1>Kassa</h1>
<c:if test="${empty sessionScope.cart || sessionScope.cart.items.size() == 0}">
    <p>Din varukorg är tom.</p>
    <p><a href="${pageContext.request.contextPath}/products">Fortsätt handla</a></p>
</c:if>
<c:if test="${not empty sessionScope.cart && sessionScope.cart.items.size() > 0}">
    <h2>Orderöversikt</h2>
    <table>
        <tr>
            <th>Produkt</th>
            <th>Pris</th>
            <th>Kvantitet</th>
            <th>Totalt</th>
        </tr>
        <c:forEach var="item" items="${sessionScope.cart.items.values()}">
            <tr>
                <td>${item.product.name}</td>
                <td>${item.product.price} SEK</td>
                <td>${item.quantity}</td>
                <td>${item.product.price * item.quantity} SEK</td>
            </tr>
        </c:forEach>
    </table>
    <p><strong>Totalpris:</strong> ${sessionScope.cart.totalPrice} SEK</p>
    <!-- Eventuell betalningsinformation -->
    <form action="${pageContext.request.contextPath}/checkout" method="post">
        <!-- Lägg till fält för betalningsinformation om nödvändigt -->
        <button type="submit">Slutför köp</button>
    </form>
</c:if>
<c:if test="${not empty param.error}">
    <p style="color:red;">${param.error}</p>
</c:if>
</body>
</html>
