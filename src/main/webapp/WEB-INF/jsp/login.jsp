<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
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
<section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">Login</h3>
			<form  action="/login" method="post" class="row needs-validation" novalidate>
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="text" name="username" id="typeEmailX-2" class="form-control form-control-lg" required/>
	              <label class="form-label" for="typeEmailX-2">Nombre de usuario</label>
	              <div class="invalid-feedback">
			          Debe ingresar un nombre de usuario valido.
			       </div>
	            </div>
	
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="password"name="password" id="typePasswordX-2" class="form-control form-control-lg" required />
	              <label class="form-label" for="typePasswordX-2">Contraseña</label>
	              <div class="invalid-feedback">
			          Debe ingresar una contraseña valida.
			       </div>
	            </div>
					
				<div class="pt-2">
			    	<c:if test="${not empty message}">
			            <div class="alert ${alertClass} alert-dismissible fade show" role="alert">
			                ${message}
			            </div>
		        	</c:if>
			    </div>	
	
	            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block" type="submit">Ingresar</button>
			</form>
			<div class="pt-2">
				<a href ="<c:url value ="/registro"/>"  > Registro</a>
			</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

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