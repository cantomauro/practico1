<%@ page contentType="text/html;charset=UTF-8" %>
<h2>HCEN — Práctico 1</h2>
<ul>
    <li><a href="${pageContext.request.contextPath}/historias">Listar historias</a></li>
</ul>

<h3>Buscar por paciente</h3>
<form action="${pageContext.request.contextPath}/historias/buscar" method="get">
    <input name="pacienteId" placeholder="pacienteId"/>
    <button type="submit">Buscar</button>
</form>

<h3>Alta Historia</h3>
<form action="${pageContext.request.contextPath}/historias/alta" method="post">
    <input name="id" placeholder="id"/>
    <input name="pacienteId" placeholder="pacienteId"/>
    <input name="prestadorId" placeholder="prestadorId"/>
    <input name="motivo" placeholder="motivo"/>
    <input name="fechaApertura" placeholder="YYYY-MM-DD"/>
    <button type="submit">Crear</button>
</form>
