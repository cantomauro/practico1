package uy.edu.fing.hcen.negocio;

import uy.edu.fing.hcen.dominio.HistoriaClinica;
import uy.edu.fing.hcen.exceptions.ReglaNegocioException;
import java.util.List;
import jakarta.ejb.Local;

public interface IHistoriaClinicaServiceLocal extends IHistoriaClinicaService
{
    void agregar(HistoriaClinica hc) throws ReglaNegocioException;
    List<HistoriaClinica> listar();
    List<HistoriaClinica> buscarPorPaciente(String pacienteId);
}
