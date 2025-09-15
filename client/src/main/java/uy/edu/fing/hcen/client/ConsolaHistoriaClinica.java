package uy.edu.fing.hcen.client;

import uy.edu.fing.hcen.dominio.HistoriaClinica;
import uy.edu.fing.hcen.negocio.IHistoriaClinicaServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.time.LocalDate;
import java.util.Hashtable;


public class ConsolaHistoriaClinica {
    public static void main(String[] args) throws Exception {
        Hashtable<String, String> jndiProps = new Hashtable<>();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        Context ctx = new InitialContext(jndiProps);

        // Con EAR finalName=hcen-practico1 y EJB jar = ejb
        String jndiName = "java:global/hcen-practico1/ejb/HistoriaClinicaService!uy.edu.fing.hcen.negocio.IHistoriaClinicaServiceRemote";
        IHistoriaClinicaServiceRemote svc = (IHistoriaClinicaServiceRemote) ctx.lookup(jndiName);

        svc.agregar(new HistoriaClinica("12345678", LocalDate.now(), "PREST01", "Motivo inicial"));
        svc.listar().forEach(h -> System.out.println(h.getId() + " - " + h.getPacienteId()));
    }
}
