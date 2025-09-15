package uy.edu.fing.hcen.web.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uy.edu.fing.hcen.dominio.HistoriaClinica;
import uy.edu.fing.hcen.negocio.IHistoriaClinicaServiceLocal;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/historias/alta")
public class AltaHistoriaServlet extends HttpServlet {
    @EJB
    private IHistoriaClinicaServiceLocal service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String pacienteId = req.getParameter("pacienteId");
        String prestadorId = req.getParameter("prestadorId");
        String motivo = req.getParameter("motivo");
        LocalDate fecha = LocalDate.parse(req.getParameter("fechaApertura"));

        service.agregar(new HistoriaClinica(pacienteId, fecha, prestadorId, motivo));
        resp.sendRedirect(req.getContextPath() + "/historias");
    }
}
