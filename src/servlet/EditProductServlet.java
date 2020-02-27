package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/editProduct" })
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditProductServlet() {
        super();
    }
 

    // Mostrar página de edición del producto.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        Product product = null;
 
        String errorString = null;
 
        try {
            product = DBUtils.findProduct(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 

     // Si no hay error.
             // El producto no existe para editar.
             // Redirigir a la página productList.
        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }
 

     // Almacenar errorString en el atributo de solicitud, antes de reenviarlo a las vistas.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
        dispatcher.forward(request, response);
 
    }
 

 // Después de que el usuario modifique la información del producto y haga clic en Enviar.
     // Este método se ejecutará.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
        String name = (String) request.getParameter("name");
        String priceStr = (String) request.getParameter("price");
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }
        Product product = new Product(code, name, price);
 
        String errorString = null;
 
        try {
            DBUtils.updateProduct(conn, product);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

     // Almacenar información para solicitar atributo, antes de reenviar a vistas.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
 

     // Si se produce un error, reenviar a Editar página.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
        }

     // Si todo está bien.
     // Redirigir a la página de listado de productos.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }
 
}