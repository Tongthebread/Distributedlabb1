<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Orderbekräftelse</title>
</head>
<body>
<h1>Tack för din beställning!</h1>
<p>Din order har mottagits och kommer att behandlas inom kort.</p>
<!-- Om orderinformation är tillgänglig -->
<c:if test="${not empty order}">
  <p><strong>Ordernummer:</strong> ${order.id}</p>
  <p><strong>Orderdatum:</strong> ${order.orderDate}</p>
  <!-- Visa orderdetaljer om nödvändigt -->
</c:if>
<p><a href="${pageContext.request.contextPath}/products">Fortsätt handla</a></p>
</body>
</html>
