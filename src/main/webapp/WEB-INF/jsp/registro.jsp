<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registro de usuarios</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>	
	<script>
        $(document).ready(function() {
            // Oculta la alerta después de 5 segundos (5000 ms)
            setTimeout(function() {
                $(".alert").alert('close');
            }, 2500);
        });
    </script>
</head>
<body>

	<div class="container pt-3" >
		<div class="row text-center"> 
	      <div class="col">
	        <h1>Registro de usuario</h1>
	      </div>
	    </div>
	    
	    <div class="card shadow">
	    	<div class="card-body">
	    		<form action="#" method="post" class="row needs-validation d-flex  justify-content-center" novalidate>
					<div class="mb-3 col-8">
						<label for="nombre" class="form-label">Nombre</label>
						<input type="text" name="nombre" class="form-control" id="nombre" required>
						<div class="invalid-feedback">
				          Debe ingresar un nombre
				        </div>
					</div>
					<div class="mb-3 col-8">
						<label for="apellido" class="form-label">Apellido</label>
						<input type="text" name="apellido" class="form-control" id="apellido" required>
						<div class="invalid-feedback">
				          Debe ingresar un apellido
				        </div>
					</div>
					<div class="mb-3 col-8">
						<label for="email" class="form-label">Email</label>
						<input type="email" name="email" class="form-control" id="email" required>
						<div class="invalid-feedback">
				          Debe ingresar un correo valido
				        </div>
					</div>
					<div class="mb-3 col-8">
						<label for="username" class="form-label">Nombre de usuario</label>
						<input type="text" name="username" class="form-control" id="username" required>
						<div class="invalid-feedback">
				          Debe ingresar un nombre de usuario.
				        </div>
					</div>
					<div class="mb-3 col-8">
						<label for="password" class="form-label">Contraseña</label>
						<input type="password" name="password" class="form-control" id="password" required>
						<div class="invalid-feedback">
				          Debe ingresar una contraseña.
				        </div>
					</div>
					
					<div class="pt-2 col-8">
				    	<c:if test="${not empty message}">
				            <div class="alert ${alertClass} alert-dismissible fade show" role="alert">
				                ${message}
				            </div>
			        	</c:if>
				    </div>
					
					<button type="submit" class="btn btn-primary col-5">Registrarse</button>
				</form>
	    	</div>
	    </div>

		<div class="row pt-2">
	      <div class="col">
	        <a href="/login" type="button" class="btn btn-primary mx-3">Volver</a>
	      </div>
	    </div>
	</div>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

  <script>
    (() => {
      'use strict'

      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      const forms = document.querySelectorAll('.needs-validation')

      // Loop over them and prevent submission
      Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }

          form.classList.add('was-validated')
        }, false)
      })
    })()
  </script>

</body>
</html>