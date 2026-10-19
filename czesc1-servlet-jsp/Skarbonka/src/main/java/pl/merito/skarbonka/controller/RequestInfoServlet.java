package pl.merito.skarbonka.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/info")
public class RequestInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><body><h1>Informacje o żądaniu</h1><ul>");
        out.println("<li>Metoda: " + req.getMethod() + "</li>");
        out.println("<li>URI: " + req.getRequestURI() + "</li>");
        out.println("<li>Kontekst aplikacji: " + req.getContextPath() + "</li>");
        out.println("<li>Ścieżka servletu: " + req.getServletPath() + "</li>");
        out.println("<li>Query string: " + req.getQueryString() + "</li>");
        out.println("<li>Adres klienta: " + req.getRemoteAddr() + "</li>");
        out.println("<li>Przeglądarka: " + req.getHeader("User-Agent") + "</li>");
        req.getParameterMap().forEach((name, values) ->
                out.println("<li>Parametr " + name + " = " + Arrays.toString(values) + "</li>"));
        out.println("</ul></body></html>");
    }
}
