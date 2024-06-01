<%@ page import="com.example.lab620202396.Beans.Pelicula" %>

<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: doria
  Date: 29/05/2024
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Pelicula> listaPeliculas = (ArrayList<Pelicula>) request.getAttribute("lista");

%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de películas</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
    <h2>Buscar Películas</h2>
    <form action="listaPeliculas" method="get">
        <input type="text" id="titleSearch" name="titleSearch" placeholder="Buscar película...">
        <input type="submit" value="Buscar">
    </form>
    <button type="button" onclick="clearFilters()">Limpiar filtros</button>

    <script>
        function clearFilters() {
            window.location.href = 'listaPeliculas';
        }
    </script>
    <h1>Lista de películas</h1>

    <table>
        <thead>
            <tr>
                <th>Título</th>
                <th>Director</th>
                <th>Año Publicación</th>
                <th>Rating</th>
                <th>BoxOffice</th>
                <th>Género</th>
                <th>Actores</th>
            </tr>
        </thead>

        <tbody>
        <% for (Pelicula pelicula : listaPeliculas) {%>
        <tr>
            <td><a href="pelicula?id=<%= pelicula.getIdPelicula() %>"><%= pelicula.getTitulo() %></a></td>
            <td><%=pelicula.getDirecto()%></td>
            <td><%=pelicula.getAnioPublicacion()%></td>
            <td><%=pelicula.getRating()%></td>
            <td>$<%=pelicula.getBoxOffice()%> </td>
            <td><%=pelicula.getNombreGenero()%></td>
            <td><a href="actores?id=<%= pelicula.getIdPelicula() %>">Ver actores</a></td>

        </tr>
        <% } %>
        </tbody>
    </table>

</body>
</html>
