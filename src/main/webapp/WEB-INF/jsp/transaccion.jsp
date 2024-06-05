<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Inicio</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container pt-5">
	    <div class="row text-center"> 
	      <div class="col">
	        <h1>Transacciones</h1>
	      </div>
	    </div>
	    <div class="row d-flex justify-content-center pt-3">
	      <div class="col-8">
	        <table class="table table-hover table-bordered border-dark">
	          <thead>
	            <tr>
	              <th scope="col">N° Transacción</th>
	              <th scope="col">Fecha</th>
	              <th scope="col">Monto</th>
	              <th scope="col">Tipo</th>
	            </tr>
	          </thead>
	          <tbody class="table-group-divider">
	          <c:forEach items="${listado}" var="transaccion">
	          	<tr>
	              <th scope="row">${transaccion.id_transaccion}</th>
	              <td>${transaccion.fecha_transaccion}</td>
	              <td>${transaccion.monto}</td>
	              <td>${transaccion.nombre_tipo}</td>
	            </tr>
	          </c:forEach>
	          </tbody>
	        </table>
	      </div>
	    </div>
	    <div class="row pt-5 text-center">
	      <div class="col">
	        <a href="/home" type="button" class="btn btn-primary mb-4">Volver al menú principal</a>
	      </div>
	    </div>
	  </div>

</body>
</html>