<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Contactos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h1>Contactos</h1>
    <c:if test="${not empty error}">
        <div style="color: red">${error}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/contactos/agregar" method="post">
        <label for="idContacto">ID del Contacto:</label>
        <input type="text" id="idContacto" name="idContacto">
        <button type="submit">Agregar Contacto</button>
    </form>
    <h2>Lista de Contactos</h2>
    <table>
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Email</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="contacto" items="${contactos}">
                <tr>
                    <td>${contacto.nombre}</td>
                    <td>${contacto.apellido}</td>
                    <td>${contacto.email}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/contactos/eliminar" method="post" style="display:inline">
                            <input type="hidden" name="idContacto" value="${contacto.id_usuario}">
                            <button type="submit">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>