<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Editar Producto</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Editar Producto</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty product}">
         <form method="POST" action="${pageContext.request.contextPath}/editProduct">
            <input type="hidden" name="code" value="${product.code}" />
            <table border="0">
               <tr>
                  <td>Codigo</td>
                  <td style="color:red;">${product.code}</td>
               </tr>
               <tr>
                  <td>Nombre</td>
                  <td><input type="text" name="name" value="${product.name}" /></td>
               </tr>
               <tr>
                  <td>Precio</td>
                  <td><input type="text" name="price" value="${product.price}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/productList">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>