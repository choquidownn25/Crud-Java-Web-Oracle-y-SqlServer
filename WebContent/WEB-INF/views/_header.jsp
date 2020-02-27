<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
  <div style="float: left">
     <h1>Mi sitio</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
 
     <!-- Almacén de usuarios en sesión con atributo Loguin -->
     Hola <b>${loginedUser.userName}</b>
   <br/>
     Buscar <input name="search">
 
  </div>
 
</div>