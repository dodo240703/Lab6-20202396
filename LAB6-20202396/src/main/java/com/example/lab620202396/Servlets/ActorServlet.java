package com.example.lab620202396.Servlets;

import com.example.lab620202396.Beans.Actor;
import com.example.lab620202396.Beans.Pelicula;
import com.example.lab620202396.Daos.PeliculaDaos;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="ActorServlet", value={"/actores","/ActorServlet"})
public class ActorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            PeliculaDaos peliculaDaos = new PeliculaDaos();
            ArrayList<Actor> listaActor = peliculaDaos.listaProtagonistasPorID(Integer.parseInt(id));


            if (listaActor != null) {
                request.setAttribute("actores", listaActor);
                RequestDispatcher view = request.getRequestDispatcher("listaActores.jsp");
                view.forward(request, response);
            } else {
                response.sendRedirect("error.jsp"); // Página de error en caso de que no se encuentre la incidencia
            }
        } else {
            response.sendRedirect("error.jsp"); // Página de error en caso de que el ID sea nulo o vacío
        }
    }
}
