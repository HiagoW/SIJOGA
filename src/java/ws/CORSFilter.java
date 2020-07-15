/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author hiago
 */
@Provider
@PreMatching
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        if(isPreflightRequest(request)){
            request.abortWith(Response.ok().build());
        }
    }
    
    private static boolean isPreflightRequest(ContainerRequestContext request){
        return request.getHeaderString("Origin") != null && request.getMethod().equalsIgnoreCase("OPTIONS");
    }
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        if(request.getHeaderString("Origin")==null){
            return;
        }
        if(isPreflightRequest(request)){
            response.getHeaders().add("Access-Control-Allow-Credentials", "true");
            response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            response.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, xxx-header");     
        }
        response.getHeaders().add("Access-Control-Allow-Origin", "localhost");
    }
    
}
