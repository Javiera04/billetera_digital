<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Contactos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>	
	<script>
        $(document).ready(function() {
            setTimeout(function() {
                $(".alert").alert('close');
            }, 2500);
        });
    </script>

</head>
<body>
	<div class="container pt-5">
		<div class="row">
			<div class="col-md-6">
				<div class="row text-center"> 
			      <div class="col">
			        <h1>Agregar contactos</h1>
			      </div>
			    </div>
		    	<form action="${pageContext.request.contextPath}/contactos/agregar" method="post" class="row g-3 needs-validation d-flex  justify-content-center" novalidate>
		        	<div class="col-md-4">
			        	<label for="nro_cuenta" class="form-label">Número de cuenta</label>
		        		<input type="text" class="form-control" id="nro_cuenta" name="nro_cuenta" required>
			        	<div class="invalid-feedback">
			          		Por favor ingrese número de cuenta del usuario que desea agregar.
			        	</div>
			        </div>	
			        <div class="col-12 text-center">
			        	<button class="btn btn-success" type="submit">Agregar Contacto</button>
			      	</div>
		    	</form>
		    	
		    	<div class="pt-2">
			    	<c:if test="${not empty message}">
			            <div class="alert ${alertClass} alert-dismissible fade show" role="alert">
			                ${message}
			            </div>
		        	</c:if>
			    </div>
		    	
			</div>
			<div class="col-md-6">
				<div class="row text-center"> 
			      <div class="col">
			        <h2>Lista de Contactos</h2>
			      </div>
			    </div>
			    <div class="row d-flex justify-content-center pt-3">
			      <div class="col-8">
			        <table class="table table-hover table-bordered border-dark">
			          <thead>
			            <tr>
			              <th scope="col">Nombre</th>
			              <th scope="col">Apellido</th>
			              <th scope="col">Email</th>
			              <th scope="col">Eliminar contacto</th>
			            </tr>
			          </thead>
			          <tbody class="table-group-divider">
			          <c:forEach items="${contactos}" var="contacto">
			          	<tr>
			              <td>${contacto.nombre}</td>
			              <td>${contacto.apellido}</td>
			              <td>${contacto.email}</td>
			              <td>
			                  <form action="${pageContext.request.contextPath}/contactos/eliminar" method="post" style="display:inline">
				                  <input type="hidden" name="idContacto" value="${contacto.id_contacto}">
				                  <button type="submit" class="btn-close" aria-label="Close"></button>
			                  </form>
			               </td>
			            </tr>
			          </c:forEach>
			          </tbody>
			        </table>
			        
			        <div class="pt-2">
				    	<c:if test="${not empty messageTwo}">
				            <div class="alert ${alertClass} alert-dismissible fade show" role="alert">
				                ${messageTwo}
				            </div>
			        	</c:if>
				    </div>
			        
			      </div>
			    </div>
			</div>	
		</div>
		
		<div class="row pt-5 text-center">
	      <div class="col">
	        <a href="/home" type="button" class="btn btn-primary mx-3">Volver al menú principal</a>
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