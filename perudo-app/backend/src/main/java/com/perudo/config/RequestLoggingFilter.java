package com.perudo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String uri = httpRequest.getRequestURI();
        
        // Log all /assets/* requests to see if they reach Spring
        if (uri.startsWith("/assets/")) {
            logger.info("🔍 ASSETS REQUEST: {} - Method: {}", uri, httpRequest.getMethod());
        }
        
        chain.doFilter(request, response);
        
        // Log response status for /assets/* requests
        if (uri.startsWith("/assets/")) {
            logger.info("📤 ASSETS RESPONSE: {} - Status: {}", uri, httpResponse.getStatus());
        }
    }
}
