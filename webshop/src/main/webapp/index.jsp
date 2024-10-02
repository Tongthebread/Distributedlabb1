<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Shop Page</title>
  <style>
    table {
      width: 50%;
      border-collapse: collapse;
    }
    table, th, td {
      border: 1px solid black;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>

<h1>Welcome to the Shop</h1>

<!-- Shop Product Table -->
<table>
  <thead>
  <tr>
    <th>Product Name</th>
    <th>Price</th>
    <th>Quantity Available</th>
    <th>Action</th>
  </tr>
  </thead>
  <tbody>
  <!-- Loop through each product and display them in the table -->
  <c:forEach var="product" items="${products}">
    <tr>
      <td>${product.name}</td>
      <td>${product.price}</td>
      <td>${product.quantity}</td>
      <td>
        <!-- Form to add product to cart -->
        <form action="/ShoppingCart-servlet" method="POST">
          <input type="hidden" name="productId" value="${product.id}" />
          <input type="number" name="quantity" value="1" min="1" max="${product.quantity}" />
          <button type="submit">Add to Cart</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<br/>
<a href="/ShoppingCart-servlet">View Cart</a>
</body>
</html>