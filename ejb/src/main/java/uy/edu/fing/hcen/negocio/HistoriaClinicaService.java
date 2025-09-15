package uy.edu.fing.hcen.negocio;

import jakarta.ejb.Stateless;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.EJB;

import uy.edu.fing.hcen.datos.IHistoriaClinicaRepository;
import uy.edu.fing.hcen.dominio.HistoriaClinica;
import uy.edu.fing.hcen.exceptions.ReglaNegocioException;

import java.time.LocalDate;
import java.util.List;

@Stateless(name = "HistoriaClinicaService")
@Local(IHistoriaClinicaServiceLocal.class)
@Remote(IHistoriaClinicaServiceRemote.class)
public class HistoriaClinicaService
        implements IHistoriaClinicaServiceLocal, IHistoriaClinicaServiceRemote {

    @EJB
    private IHistoriaClinicaRepository repo;

    @Override
    public void agregar(HistoriaClinica hc) throws ReglaNegocioException {
        validar(hc);
        hc.setId(null);
        repo.agregar(hc);
    }

    @Override
    public List<HistoriaClinica> listar() {
        return repo.listar();
    }

    @Override
    public List<HistoriaClinica> buscarPorPaciente(String pacienteId) {
        return repo.buscarPorPaciente(pacienteId);
    }

    // ↓↓↓ ESTE método va DENTRO de la clase ↓↓↓
    private void validar(HistoriaClinica hc) throws ReglaNegocioException {
        if (hc == null) throw new ReglaNegocioException("HC nula");
        if (hc.getPacienteId() == null || hc.getPacienteId().isBlank())
            throw new ReglaNegocioException("Paciente requerido");
        if (hc.getFechaApertura() == null)
            throw new ReglaNegocioException("Fecha de apertura requerida");
        if (hc.getFechaApertura().isAfter(LocalDate.now()))
            throw new ReglaNegocioException("Fecha futura no permitida");
        // Si tu modelo exige id no nulo al crear:
        // if (hc.getId() == null) throw new ReglaNegocioException("id requerido");
    }
}