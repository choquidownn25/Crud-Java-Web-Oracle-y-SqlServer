<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Inicio Pagina</title>
  </head>
  <body>
 
     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="_menu.jsp"></jsp:include>
    
      <h3>Pagina Inicio</h3>
      
      
	  Esta es una aplicación web simple de demostración usando jsp, servlet &amp; Jdbc. <br><br>
      <b>Incluye las siguientes funciones:</b>
      <ul>
         <li>Login</li>
         <li>Almacenar información del usuario en cookies</li>
         <li>Lista Productos</li>
         <li>Crear Productos</li>
         <li>Editar Productos</li>
         <li>Eliminar Productos</li>
      </ul>
 
     <jsp:include page="_footer.jsp"></jsp:include>
 
  </body>
</html>