package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            TimeZone timeZone = TimeZone.getTimeZone("UTC");
            String timeZoneParam = request.getParameter("timezone");
            TimeZone timeZone;

            if (timeZoneParam != null && timeZoneParam.isEmpty()) {
                timeZoneParam = URLEncoder.encode(timeZoneParam, "UTF-8");
                timeZone = TimeZone.getTimeZone(timeZoneParam);
            } else {
                timeZone = TimeZone.getTimeZone("UTC");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            sdf.setTimeZone(timeZone);

            String currentTime = sdf.format(new Date());

            String htmlResponse = "<html><body><h2> Time: " + currentTime + "</h2></body></html>";

            response.setContentType("text/html");
            response.getWriter().write(htmlResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
