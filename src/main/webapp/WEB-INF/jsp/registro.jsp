<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro de usuarios</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<form action="#" method="post">
			<div class="mb-3">
				<label for="nombre" class="form-label">Nombre</label>
				<input type="text" name="nombre" class="form-control" id="nombre">
			</div>
			<div class="mb-3">
				<label for="apellido" class="form-label">Apellido</label>
				<input type="text" name="apellido" class="form-control" id="apellido">
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Email</label>
				<input type="email" name="email" class="form-control" id="email">
			</div>
			<div class="mb-3">
				<label for="username" class="form-label">username</label>
				<input type="text" name="username" class="form-control" id="username">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">password</label>
				<input type="password" name="password" class="form-control" id="password">
			</div>
			<button type="submit" class="btn btn-primary">Ingresar</button>
		</form>
	</div>

</body>
</html>