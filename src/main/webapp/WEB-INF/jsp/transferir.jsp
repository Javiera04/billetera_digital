<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Transferir</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

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
	<div class="container pt-5">
		<div class="row text-center"> 
	      <div class="col">
	        <h1>Transferir</h1>
	      </div>
	    </div>
	    <form action="/cuenta/transferir" method="post" class="row g-3 needs-validation d-flex justify-content-center" novalidate>
	        <div class="col-md-4">
		        <label for="cuenta_destino" class="form-label">Selecciona un Contacto</label>
		        <select class="form-select" id="cuenta_destino" name="cuenta_destino" required>
			      <option selected disabled value="">Lista de contactos...</option>
			      <c:forEach var="contacto" items="${contactos}">
		              <option value="${contacto.id_contacto}">${contacto.nombre} ${contacto.apellido}</option>
		          </c:forEach>
			    </select>
			    <div class="invalid-feedback">
			      Debe seleccionar un contacto.
			    </div>
	        </div>
	        <div class="col-md-4">
	        	<label for="monto_transferencia" class="form-label">Monto</label>
	        	<input type="number" class="form-control" id="monto_transferencia" name="monto_transferencia" required>
	        	<div class="invalid-feedback">
		          Para transferir se necesita un monto.
		        </div>
	        </div>
	        <div class="col-12 text-center">
		        <button class="btn btn-danger" type="submit">Transferir</button>
		    </div>
	    </form>
	    
	    <div class="pt-2">
	    	<c:if test="${not empty message}">
	            <div class="alert ${alertClass} alert-dismissible fade show" role="alert">
	                ${message}
	            </div>
        	</c:if>
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