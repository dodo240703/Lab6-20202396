<%--
  Created by IntelliJ IDEA.
  User: doria
  Date: 30/05/2024
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.lab620202396.Beans.Pelicula" %>
<%@ page import="com.example.lab620202396.Beans.Pelicula" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
%>
<html>
<head>
    <title>Pelicula</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
    <h1><%= pelicula.getTitulo() %></h1>
    <table>
        <tbody>
        <tr>
            <td><b>idPelicula</b></td>
            <td><%=pelicula.getIdPelicula()%></td>
        </tr>
        <tr>
            <td><b>Director</b></td>
            <td><%=pelicula.getDirecto()%></td>
        </tr>
        <tr>
            <td><b>Año Publicación</b></td>
            <td><%=pelicula.getAnioPublicacion()%></td>
        </tr>
        <tr>
            <td><b>Rating</b></td>
            <td><%=pelicula.getRating()%></td>
        </tr>
        <tr>
            <td><b>BoxOffice</b></td>
            <td><%=pelicula.getBoxOffice()%></td>
        </tr>
        <tr>
            <td><b>Género</b></td>
            <td><%=pelicula.getNombreGenero()%></td>
        </tr>
        <tr>
            <td><b>Actores</b></td>
            <td><a href="actores?id=<%= pelicula.getIdPelicula() %>">Ver actores</a></td>
        </tr>
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
