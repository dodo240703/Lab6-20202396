package com.example.lab620202396.Servlets;



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

@WebServlet(name="PeliculaServlet", value={"/listaPeliculas","/PeliculaServlet"})
public class PeliculaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PeliculaDaos peliculaDaos = new PeliculaDaos();
        ArrayList<Pelicula> listarPeliculas;
        String titleSearch = request.getParameter("titleSearch");

        if (titleSearch != null && !titleSearch.trim().isEmpty()) {
            listarPeliculas = peliculaDaos.filterPeliculas(titleSearch);
        } else {
            listarPeliculas = peliculaDaos.listarPeliculas();
        }
        request.setAttribute("lista", listarPeliculas);

        RequestDispatcher view = request.getRequestDispatcher("tablaPeliculas.jsp");
        view.forward(request,response);


    }

}

