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
import javax.servlet.http.HttpSession;

import models.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	 
    public LoginServlet() {
        super();
    }
 
    // Mostrar página de inicio de sesión.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Reenviar a  /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 

     // Cuando el usuario ingrese nombre de usuario y contraseña, y haga clic en Enviar.
     // Este método se ejecutará.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {

            	// Encuentra el usuario en la base de datos.
                user = DBUtils.findUser(conn, userName, password);
 
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // Si hay error, reenviar a /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
 
         // Almacenar información en el atributo de solicitud, antes de reenviar.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Reenviar a /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }

        	 // Si no hay error
             // Almacenar información del usuario en la sesión
             // Y redirigir a la página userInfo.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
 
         // Si la usuario marcó "Recordarme".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Delo contrario eliminar cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }
 
         // Redirigir a la página de información del usuario.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
    
}
