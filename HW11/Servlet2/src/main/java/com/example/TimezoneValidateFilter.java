package com.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.Filter;

import java.io.IOException;
import java.util.TimeZone;

@WebFilter(urlPatterns = {"/time"})
public class TimezoneValidateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
           HttpServletRequest httpServletRequest = (HttpServletRequest) request;
           String timeZone = httpServletRequest.getParameter("timezone");

           if (isValidTimeZone(timeZone)) {
               chain.doFilter(request, response);
           } else {
               HttpServletResponse httpServletResponse = (HttpServletResponse) response;
               httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
               httpServletResponse.getWriter().write("Invalid timezone");
           }
        }
    }

    private boolean isValidTimeZone(String timezone) {
        String[] availableTimeZones = TimeZone.getAvailableIDs();
        for (String availableTimeZone : availableTimeZones) {
            if (availableTimeZone.equals(timezone)) {
                return true;
            }
        }
        return false;
    }
}
