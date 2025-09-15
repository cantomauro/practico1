<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, uy.edu.fing.hcen.dominio.HistoriaClinica" %>
<%
    List<HistoriaClinica> historias = (List<HistoriaClinica>) request.getAttribute("historias");
%>
<h3>Listado de Historias</h3>
<table border="1" cellpadding="4">
    <tr><th>ID</th><th>Paciente</th><th>Fecha</th><th>Prestador</th><th>Motivo</th></tr>
    <%
        if (historias != null) {
            for (HistoriaClinica h : historias) {
    %>
    <tr>
        <td><%=h.getId()%></td>
        <td><%=h.getPacienteId()%></td>
        <td><%=h.getFechaApertura()%></td>
        <td><%=h.getPrestadorId()%></td>
        <td><%=h.getMotivo()%></td>
    </tr>
    <%
            }
        }
    %>
</table>
<p><a href="<%=request.getContextPath()%>/">Volver</a></p>
