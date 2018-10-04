package com.oauth2.config;

/**
 * Created by Dégi János on 2018.01.19..
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

//     https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS
//     https://stackoverflow.com/questions/34949492/cors-request-with-preflight-and-redirect-disallowed-workarounds

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String requestOrigin = httpServletRequest.getHeader("Origin");
//      response.setHeader("Access-Control-Allow-Origin", "*");
//      response.setHeader("Access-Control-Allow-Origin", "http://localhost:8100");
        response.setHeader("Access-Control-Allow-Origin", requestOrigin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
