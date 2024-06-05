<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Transferir</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h1>Transferir Dinero</h1>
    <form action="/cuenta/transferir" method="post">
        <label for="cuenta_destino">ID de Cuenta Destino:</label>
        <input type="text" id="cuenta_destino" name="cuenta_destino" required><br><br>
        <label for="monto_transferencia">Monto:</label>
        <input type="text" id="monto_transferencia" name="monto_transferencia" required><br><br>
        <input type="submit" value="Transferir">
    </form>
    
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>

</body>
</html>