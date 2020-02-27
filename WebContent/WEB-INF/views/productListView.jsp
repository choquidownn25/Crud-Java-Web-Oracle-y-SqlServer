<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <title>Lista Producto</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Lista Producto</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Codigo</th>
          <th>Nombre</th>
          <th>Precio</th>
          <th>Editar</th>
          <th>Eliminar</th>
       </tr>
       <c:forEach items="${productList}" var="product" >
          <tr>
             <td>${product.code}</td>
             <td>${product.name}</td>
             <td>${product.price}</td>
             <td>
                <a href="editProduct?code=${product.code}">Edit</a>
             </td>
             <td>
                <a href="deleteProduct?code=${product.code}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createProduct" >Crear Producto</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>