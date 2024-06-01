package com.example.lab620202396.Daos;

import com.example.lab620202396.Beans.Actor;
import com.example.lab620202396.Beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDaos {

    public static ArrayList<Pelicula> listarPeliculas() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "root";

        String sql = "select p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre as genero, p.idGenero\n" +
                "from pelicula p\n" +
                "join genero g on p.idGenero = g.idGenero\n" +
                "ORDER BY p.rating DESC, p.boxOffice DESC;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt(1));
                pelicula.setTitulo(rs.getString(2));
                pelicula.setDirecto(rs.getString(3));
                pelicula.setAnioPublicacion(rs.getInt(4));
                pelicula.setRating(rs.getDouble(5));
                pelicula.setBoxOffice(rs.getDouble(6));
                pelicula.setNombreGenero(rs.getString(7));
                pelicula.setIdGenero(rs.getInt(8));



                listaPeliculas.add(pelicula);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public static ArrayList<Pelicula> filterPeliculas(String initialsTitulo){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        ArrayList<Pelicula> peliculasFiltradas = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "root";

        String sql = "select p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre as genero, p.idGenero\n" +
                "                from pelicula p\n" +
                "                join genero g on p.idGenero = g.idGenero\n" +
                "\t\t\t\twhere p.titulo like ?\n" +
                "                ORDER BY p.rating DESC, p.boxOffice DESC; ";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,  initialsTitulo + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt(1));
                    pelicula.setTitulo(rs.getString(2));
                    pelicula.setDirecto(rs.getString(3));
                    pelicula.setAnioPublicacion(rs.getInt(4));
                    pelicula.setRating(rs.getDouble(5));
                    pelicula.setBoxOffice(rs.getDouble(6));
                    pelicula.setNombreGenero(rs.getString(7));
                    pelicula.setIdGenero(rs.getInt(8));

                    peliculasFiltradas.add(pelicula);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculasFiltradas;
    }

    public Pelicula obtenerPeliculaPorId(int id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        Pelicula pelicula = null;
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "root";

        String sql = "select p.titulo, p.idPelicula, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre as genero\n" +
                "from pelicula p\n" +
                "join genero g on p.idGenero = g.idGenero\n" +
                "where p.idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pelicula = new Pelicula();
                    pelicula.setTitulo(rs.getString(1));
                    pelicula.setIdPelicula(rs.getInt(2));
                    pelicula.setDirecto(rs.getString(3));
                    pelicula.setAnioPublicacion(rs.getInt(4));
                    pelicula.setRating(rs.getDouble(5));
                    pelicula.setBoxOffice(rs.getDouble(6));
                    pelicula.setNombreGenero(rs.getString(7));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pelicula;
    }

    public ArrayList<Actor> listaProtagonistasPorID(int id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        ArrayList<Actor> listaProtagonistas = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "root";

        String sql = "SELECT a.idActor, a.Nombre, a.Apellido, a.anoNacimiento, a.premioOscar, p.idPelicula, t.titulo\n" +
                "FROM actor a\n" +
                "JOIN protagonistas p ON a.idActor = p.idActor\n" +
                "JOIN pelicula t ON p.idPelicula = t.idPelicula\n" +
                "WHERE p.idPelicula = ?;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Actor actor = new Actor();
                    actor.setIdActor(rs.getInt(1));
                    actor.setNombre(rs.getString(2));
                    actor.setApellido(rs.getString(3));
                    actor.setAnioNacimiento(rs.getInt(4));
                    actor.setPremioOscar(rs.getBoolean(5));
                    actor.setIdPelicula(rs.getInt(6));
                    actor.setTituloPelicula(rs.getString(7));




                    listaProtagonistas.add(actor);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaProtagonistas;
    }
}
