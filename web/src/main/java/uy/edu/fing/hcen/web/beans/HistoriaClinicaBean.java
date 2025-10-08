package uy.edu.fing.hcen.web.beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import uy.edu.fing.hcen.dominio.HistoriaClinica;
import uy.edu.fing.hcen.negocio.IHistoriaClinicaServiceLocal;
import uy.edu.fing.hcen.negocio.IHistoriaClinicaServiceRemote;

// importa tu entidad e interfaz del módulo api
import uy.edu.fing.hcen.api.entidades.HistoriaClinica;
import uy.edu.fing.hcen.api.servicios.IHistoriaClinicaService;

@Named("historiaClinicaBean")
@ViewScoped
public class HistoriaClinicaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHistoriaClinicaServiceRemote historiaService;

    // Campos del formulario "Alta"
    private Long pacienteId;
    private Long prestadorId;
    private String motivo;

    // Listado y filtro
    private List<HistoriaClinica> historias;
    private Long filtroPacienteId;

    @PostConstruct
    public void init() {
        try {
            historias = historiaService.listar();
        } catch (Exception e) {
            historias = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error cargando historias", e.getMessage()));
        }
    }

    public void agregar() {
        try {
            HistoriaClinica hc = new HistoriaClinica();
            hc.setPacienteId(pacienteId);
            hc.setPrestadorId(prestadorId);
            hc.setFechaApertura(LocalDate.now());
            hc.setMotivo(motivo);

            historiaService.agregar(hc);
            limpiarFormulario();
            historias = historiaService.listar();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Historia creada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo crear", e.getMessage()));
        }
    }

    public void buscarPorPaciente() {
        try {
            if (filtroPacienteId == null) {
                historias = historiaService.listar();
            } else {
                historias = historiaService.buscarPorPacienteId(filtroPacienteId);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en búsqueda", e.getMessage()));
        }
    }

    public void limpiarFormulario() {
        pacienteId = null;
        prestadorId = null;
        motivo = null;
    }

    // getters/setters
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getPrestadorId() { return prestadorId; }
    public void setPrestadorId(Long prestadorId) { this.prestadorId = prestadorId; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public List<HistoriaClinica> getHistorias() { return historias; }
    public void setHistorias(List<HistoriaClinica> historias) { this.historias = historias; }
    public Long getFiltroPacienteId() { return filtroPacienteId; }
    public void setFiltroPacienteId(Long filtroPacienteId) { this.filtroPacienteId = filtroPacienteId; }
}
