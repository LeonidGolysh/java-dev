package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        templateEngine = new TemplateEngine();

        FileTemplateResolver templateResolver = new FileTemplateResolver();

//        Properties properties = new Properties();
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
//            properties.load(input);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String templatePath = properties.getProperty("template.path");
//        if (templatePath != null) {
//            templateResolver.setPrefix(templatePath);
//
//        } else {
//            templateResolver.setPrefix("C:\\Users\\Leonid\\OneDrive\\Рабочий стол\\New folder (2)\\GoIt\\java_dev\\HW12\\Cookies\\templates/");
//        }
        templateResolver.setPrefix("C:\\Users\\Leonid\\OneDrive\\Рабочий стол\\New folder (2)\\GoIt\\java_dev\\HW12\\Cookies\\templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);

        templateEngine.addTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timeZoneParam = request.getParameter("timezone");
        String currentTime;

        if (timeZoneParam != null && !timeZoneParam.isEmpty()) {
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneParam);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            sdf.setTimeZone(timeZone);
            currentTime = sdf.format(new Date());

//            String encodedTimeZone = URLEncoder.encode(timeZoneParam, "UTF-8");

            timeZoneParam = URLEncoder.encode(timeZoneParam, "UTF-8");
            Cookie cookie = new Cookie("lastTimezone", timeZoneParam);
            response.addCookie(cookie);
        } else {
            String lastTimezone = getLastTimezoneFromCookies(request);

            if (lastTimezone != null) {
                TimeZone timeZone = TimeZone.getTimeZone(lastTimezone);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                sdf.setTimeZone(timeZone);
                currentTime = sdf.format(new Date());
            } else {
                TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                sdf.setTimeZone(utcTimeZone);
                currentTime = sdf.format(new Date());
            }
        }

        Context ctx = new Context();
        ctx.setVariable("currentTime", currentTime);

        String template = "time";
        String html = templateEngine.process(template, ctx);
        response.getWriter().write(html);
    }

    private String getLastTimezoneFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastTimezone")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
