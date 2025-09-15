package uy.edu.fing.hcen.datos;

import jakarta.ejb.Local;
import uy.edu.fing.hcen.dominio.HistoriaClinica;
import java.util.List;

@Local
public interface IHistoriaClinicaRepository {
    void agregar(HistoriaClinica hc);                 // crear/guardar
    List<HistoriaClinica> listar();                   // todas
    List<HistoriaClinica> buscarPorPaciente(String pacienteId);
}
