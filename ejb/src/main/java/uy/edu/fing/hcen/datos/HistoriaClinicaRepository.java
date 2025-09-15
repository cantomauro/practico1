package uy.edu.fing.hcen.datos;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uy.edu.fing.hcen.dominio.HistoriaClinica;

import java.util.List;

@Stateless
public class HistoriaClinicaRepository implements IHistoriaClinicaRepository {

    @PersistenceContext(unitName = "hcenPU")
    private EntityManager em;

    @Override
    public void agregar(HistoriaClinica hc) {
        if (hc.getId() != null) {
            throw new IllegalArgumentException("Para alta, el id debe ser null");
        }
        em.persist(hc);
    }

    @Override
    public List<HistoriaClinica> listar() {
        return em.createQuery("select h from HistoriaClinica h", HistoriaClinica.class)
                .getResultList();
    }

    @Override
    public List<HistoriaClinica> buscarPorPaciente(String pacienteId) {
        return em.createQuery(
                        "select h from HistoriaClinica h where h.pacienteId = :pid",
                        HistoriaClinica.class)
                .setParameter("pid", pacienteId)
                .getResultList();
    }
}
