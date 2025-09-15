package uy.edu.fing.hcen.dominio;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "HISTORIA_CLINICA")
public class HistoriaClinica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para H2 va bien IDENTITY
    private Long id;

    @Column(nullable = false)
    private String pacienteId;

    private LocalDate fechaApertura;

    private String prestadorId;

    private String motivo;

    // JPA exige ctor sin args: public o protected
    protected HistoriaClinica() {}

    public HistoriaClinica(String pacienteId, LocalDate fechaApertura, String prestadorId, String motivo) {
        this.pacienteId = pacienteId;
        this.fechaApertura = fechaApertura;
        this.prestadorId = prestadorId;
        this.motivo = motivo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPacienteId() { return pacienteId; }
    public void setPacienteId(String pacienteId) { this.pacienteId = pacienteId; }

    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate fechaApertura) { this.fechaApertura = fechaApertura; }

    public String getPrestadorId() { return prestadorId; }
    public void setPrestadorId(String prestadorId) { this.prestadorId = prestadorId; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoriaClinica)) return false;
        HistoriaClinica that = (HistoriaClinica) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
