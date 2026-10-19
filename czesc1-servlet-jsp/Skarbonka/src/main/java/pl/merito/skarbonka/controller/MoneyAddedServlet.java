package pl.merito.skarbonka.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/money/added")
public class MoneyAddedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE html><html><body>"
                + "<h1>Zapisano wpłatę na cel: " + req.getParameter("target") + "</h1>"
                + "<a href='add'>Dodaj kolejną</a></body></html>");
    }
}