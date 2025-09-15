package uy.edu.fing.hcen.web.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uy.edu.fing.hcen.negocio.IHistoriaClinicaServiceLocal;

import java.io.IOException;

@WebServlet("/historias")
public class ListarHistoriasServlet extends HttpServlet {
    @EJB private IHistoriaClinicaServiceLocal service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("historias", service.listar());
        req.getRequestDispatcher("/WEB-INF/historias/listar.jsp").forward(req, resp);
    }
}
