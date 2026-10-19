package pl.merito.skarbonka.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/money/add")
public class MoneyFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        renderForm(resp, new ArrayList<>(), "", "", "");     // pusty formularz
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        String target = trim(req.getParameter("target"));
        String amount = trim(req.getParameter("amount"));
        String date = trim(req.getParameter("date"));

        // walidacja po stronie serwera
        List<String> errors = new ArrayList<>();
        if (target.isEmpty()) {
            errors.add("Cel jest wymagany.");
        }

        // Potwierdzenie kwoty
        try {
            double parsedAmount = Double.parseDouble(amount);
            if (parsedAmount <= 0) {
                errors.add("Kwota musi być większa od zera.");
            }
        } catch (NumberFormatException e) {
            errors.add("Kwota musi być poprawną liczbą.");
        }

        if (date.isEmpty()) {
            errors.add("Data wpłacenia jest wymagana.");
        }

        if (!errors.isEmpty()) {
            renderForm(resp, errors, target, amount, date);   // pokaż formularz z błędami
            return;
        }

        // sukces: przekierowanie
        String encoded = URLEncoder.encode(target, StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/money/added?target=" + encoded);
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private void renderForm(HttpServletResponse resp, List<String> errors,
                            String target, String amount, String date) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><body><h1>Dodaj wpłatę do skarbonki</h1>");
        for (String error : errors) {
            out.println("<p style='color:red'>" + error + "</p>");
        }
        out.println("<form method='post'>");
        out.println("<p>Cel: <input name='target' value='" + target + "'></p>");
        out.println("<p>Kwota: <input name='amount' value='" + amount + "'></p>");
        out.println("<p>Data wpłacenia: <input name='date' type='date' value='" + date + "'></p>");
        out.println("<button type='submit'>Zapisz</button></form></body></html>");
    }
}