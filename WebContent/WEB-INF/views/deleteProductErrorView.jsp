<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Eliminar Producto</title>
 </head>
 
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    
    <h3>Eliminar Producto</h3>
    
    <p style="color: red;">${errorString}</p>
    <a href="productList">Lista Producto</a>
    
    <jsp:include page="_footer.jsp"></jsp:include>
    
 </body>
</html>