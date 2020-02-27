package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserAccount;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserInfoServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
 
     // Comprobar que el usuario ha iniciado sesi�n
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
 
     // Sin iniciar sesi�n
        if (loginedUser == null) {

        	// Redirigir a la p�gina de inicio de sesi�n.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
     // Almacenar informaci�n en el atributo de solicitud antes de reenviar.
        request.setAttribute("user", loginedUser);
 

     // Si el usuario ha iniciado sesi�n, reenv�e a la p�gina
        // /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
