package uy.edu.fing.hcen.web.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uy.edu.fing.hcen.negocio.IHistoriaClinicaServiceLocal;

import java.io.IOException;

@WebServlet("/historias/buscar")
public class BuscarHistoriasServlet extends HttpServlet {
    @EJB private IHistoriaClinicaServiceLocal service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pacienteId = req.getParameter("pacienteId");
        req.setAttribute("historias", service.buscarPorPaciente(pacienteId));
        req.getRequestDispatcher("/WEB-INF/historias/buscar.jsp").forward(req, resp);
    }
}