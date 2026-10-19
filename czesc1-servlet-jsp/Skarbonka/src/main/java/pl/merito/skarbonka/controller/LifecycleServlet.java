package pl.merito.skarbonka.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/lifecycle", loadOnStartup = 1)
public class LifecycleServlet extends HttpServlet {

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public void init() {
        log("init(): servlet skarbonki utworzony");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int n = counter.incrementAndGet();
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("Obsłużono żądań od uruchomienia: " + n);
    }

    @Override
    public void destroy() {
        log("destroy(): servlet usuwany, obsłużono " + counter.get() + " żądań");
    }
}