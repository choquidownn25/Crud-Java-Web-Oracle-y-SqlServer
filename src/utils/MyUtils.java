package utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserAccount;
 

 
public class MyUtils {
 
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
 
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
 

     // Almacenar la conexión en el atributo de solicitud.
     // (La información almacenada solo existe durante las solicitudes)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 

 // Obtener el objeto Connection ha sido almacenado en el atributo de la solicitud.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
 

 // Almacenar información del usuario en la sesión.
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {

    	// En el JSP puede acceder a través de ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }
 

 // Obtenga la información del usuario almacenada en la sesión.
    public static UserAccount getLoginedUser(HttpSession session) {
        UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
        return loginedUser;
    }
 
    // Almacena info en Cookie
    public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());

     // 1 día (convertido a segundos)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }
 
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    // Eliminar cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);

     // 0 segundos (esta cookie caducará inmediatamente)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
 
}