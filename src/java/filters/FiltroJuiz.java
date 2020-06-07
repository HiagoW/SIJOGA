/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import beans.Usuario;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managedBeans.LoginMB;

/**
 *
 * @author hiago
 */
@WebFilter("/faces/juiz/*")
public class FiltroJuiz implements Filter {
    
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
            Usuario usuario = null;
            HttpSession sess = ((HttpServletRequest) request).getSession(false);
            if (sess != null){
                usuario = (Usuario) sess.getAttribute("usuarioLogado");
            }    
            
             if (usuario == null) {
              String contextPath = ((HttpServletRequest) request)
                                 .getContextPath();
              ((HttpServletResponse) response).sendRedirect(contextPath
                                 + "/faces/login.xhtml");
            } else {
                     chain.doFilter(request, response);
            }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {

    }

    public FiltroJuiz() {
    }
    
    
    
}
