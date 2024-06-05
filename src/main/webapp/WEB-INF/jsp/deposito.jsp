<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Depósito</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container pt-5">
	    <div class="row text-center"> 
	      <div class="col">
	        <h1>Depositar</h1>
	      </div>
	    </div>
	    <form action="#" method="post" class="row g-3 needs-validation d-flex  justify-content-center" novalidate>
	      <div class="col-md-4">
	        <label for="monto_deposito" class="form-label">Monto a depositar</label>
	        <input type="number" class="form-control" id="monto_deposito" name="monto_deposito" required>
	        <div class="invalid-feedback">
	          Para depositar se necesita un monto.
	        </div>
	      </div>
	      <div class="col-12 text-center">
	        <button class="btn btn-success" type="submit">Realizar deposito</button>
	      </div>
	    </form>
	    
	    <c:if test="${not empty message}">
        	<p>${message}</p>
    	</c:if>
    	
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