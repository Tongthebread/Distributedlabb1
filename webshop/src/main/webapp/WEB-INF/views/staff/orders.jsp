<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Orderhantering</title>
</head>
<body>
<h1>Orderhantering</h1>
<table>
  <tr>
    <th>Order ID</th>
    <th>Användar-ID</th>
    <th>Datum</th>
    <th>Status</th>
    <th>Åtgärder</th>
  </tr>
  <c:forEach var="order" items="${orders}">
    <tr>
      <td>${order.id}</td>
      <td>${order.userId}</td>
      <td>${order.orderDate}</td>
      <td>${order.status}</td>
      <td>
        <form action="${pageContext.request.contextPath}/orders" method="post">
          <input type="hidden" name="orderId" value="${order.id}">
          <select name="status">
            <option value="pending" ${order.status == 'pending' ? 'selected' : ''}>Pending</option>
            <option value="processed" ${order.status == 'processed' ? 'selected' : ''}>Processed</option>
            <option value="shipped" ${order.status == 'shipped' ? 'selected' : ''}>Shipped</option>
            <option value="delivered" ${order.status == 'delivered' ? 'selected' : ''}>Delivered</option>
            <!-- Lägg till fler statusar vid behov -->
          </select>
          <button type="submit">Uppdatera Status</button>
        </form>
      </td>
    </tr>
    <!-- Visa orderrader om nödvändigt -->
  </c:forEach>
</table>
</body>
</html>
