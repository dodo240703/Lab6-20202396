<%@ page import="com.example.lab620202396.Beans.Actor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab620202396.Beans.Pelicula" %><%--
  Created by IntelliJ IDEA.
  User: doria
  Date: 30/05/2024
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Actor> listaActores = (ArrayList<Actor>) request.getAttribute("actores");
%>

<html>
<head>
    <title>Lista de Actores</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

    <h1><%= listaActores.get(0).getTituloPelicula() %></h1>

    <table>
        <thead>
            <tr>
                <th><b>idActor</b></th>
                <th><b>Nombre</b></th>
                <th><b>Apellido</b></th>
                <th><b>Ano nacimiento</b></th>
                <th><b>Ganador Premio Oscar</b></th>
            </tr>
        </thead>

        <tbody>
            <% for (Actor actor : listaActores) { %>
            <tr>
                <td><%= actor.getIdActor() %></td>
                <td><%= actor.getNombre() %></td>
                <td><%= actor.getApellido() %></td>
                <td><%= actor.getAnioNacimiento() %></td>
                <td><%= actor.isPremioOscar() ? "Sí" : "No" %></td>

            </tr>
            <% } %>
        </tbody>
    </table>

    <button type="button" onclick="clearFilters()">Volver</button>

    <script>
        function clearFilters() {
            window.location.href = 'listaPeliculas';
        }
    </script>

</body>
</html>
