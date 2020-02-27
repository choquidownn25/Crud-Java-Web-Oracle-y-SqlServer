package filter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import conn.ConnectionUtils;
import utils.MyUtils;
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {

	 public JDBCFilter() {
	    }
	 
	    @Override
	    public void init(FilterConfig fConfig) throws ServletException {
	 
	    }
	 
	    @Override
	    public void destroy() {
	 
	    }
	 
	    // Check el objetivo de la solicitud es un servlet?
	    private boolean needJDBC(HttpServletRequest request) {
	        System.out.println("JDBC Filter");
	        // 
	        // Servlet Url-pattern: /spath/*
	        // 
	        // => /spath
	        String servletPath = request.getServletPath();
	        // => /abc/mnp
	        String pathInfo = request.getPathInfo();
	 
	        String urlPattern = servletPath;
	 
	        if (pathInfo != null) {
	            // => /spath/*
	            urlPattern = servletPath + "/*";
	        }
	 
	        // Key: servletName.
	        // Value: ServletRegistration
	        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
	                .getServletRegistrations();
	 
	        //Colección de todos servlet en tu Webapp.
	        Collection<? extends ServletRegistration> values = servletRegistrations.values();
	        for (ServletRegistration sr : values) {
	            Collection<String> mappings = sr.getMappings();
	            if (mappings.contains(urlPattern)) {
	                return true;
	            }
	        }
	        return false;
	    }
	 
	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	 
	        HttpServletRequest req = (HttpServletRequest) request;
	 

	     // Solo se abren conexiones para las solicitudes especiales.
	     // (Por ejemplo, la ruta al servlet, JSP, ..)
	     //
	     // Evite la conexión abierta para solicitud de bienes comunes.
	     // (Para ejemplo: image, css, javascript,... )
	     // 
	        if (this.needJDBC(req)) {
	 
	            System.out.println("Open Connection for: " + req.getServletPath());
	 
	            Connection conn = null;
	            try {

	            	// Crear una conexión.
	                conn = ConnectionUtils.getConnection();
	                // Establecer outo comprometerse a falsa.
	                conn.setAutoCommit(false);
	 
	             // Almacenar objeto de conexión en el atributo de solicitud.
	                MyUtils.storeConnection(request, conn);
	 
	             // Permitir que la solicitud avance
	             // (Ir al siguiente filtro u objetivo)
	                chain.doFilter(request, response);
	 

	             // Invoque el método commit () para completar la transacción con el DB.
	                conn.commit();
	            } catch (Exception e) {
	                e.printStackTrace();
	                ConnectionUtils.rollbackQuietly(conn);
	                throw new ServletException();
	            } finally {
	                ConnectionUtils.closeQuietly(conn);
	            }
	        }

	     // Con solicitudes comunes (imágenes, css, html, ..)
	     // No es necesario abrir la conexión.
	        else {

	        	// Permitir que la solicitud avance
	        	// (Ir al siguiente filtro u objetivo)
	            chain.doFilter(request, response);
	        }
	 
	    }
}
