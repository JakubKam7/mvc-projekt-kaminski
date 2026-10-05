package pl.merito.skarbonka.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")   // adres, pod którym działa servlet
public class HelloServlet extends HttpServlet {

    // GET – np. wejście z paska adresu lub kliknięcie linku
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><body>");
        out.println("<h1>Witaj w Bibliotece!</h1>");
        out.println("<p>To jest odpowiedź na żądanie GET.</p>");
        out.println("<a href='index.html'>Powrót</a>");
        out.println("</body></html>");
    }

    // POST – wysłanie formularza
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");          // polskie znaki z formularza
        String name = req.getParameter("name");     // wartość pola name="name"

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><body>");
        out.println("<h1>Cześć, " + name + "!</h1>");
        out.println("<p>To jest odpowiedź na żądanie POST.</p>");
        out.println("<a href='index.html'>Powrót</a>");
        out.println("</body></html>");
    }
}
