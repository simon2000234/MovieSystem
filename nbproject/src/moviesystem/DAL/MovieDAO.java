/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moviesystem.BE.Movie;

/**
 *
 * @author Melchertsen
 */
public class MovieDAO
{

    private DBConnectionProvider dbConnect;

    public MovieDAO()
    {
        dbConnect = new DBConnectionProvider();
    }

    public void createMovie(String name, double rating, String filePath, int categoryId) throws SQLException
    {
        if (rating < 0.0 || rating >10.0)
        {
            System.out.println("kun 0.0 til 10.0 dit fjols");
            return;
        }
        
        String sql = "INSERT INTO Movie(name, rating, filePath) VALUES (?,?,?);";
        String sql2 = "INSERT INTO CatMov(CategoryId, MovieId) VALUES (?,?);";
        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setDouble(2, rating);
            st.setString(3, filePath);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            int movId = 0;
            if (rs.next())
            {
                movId = rs.getInt(1);
            }
            PreparedStatement st2 = con.prepareStatement(sql2);
            st2.setInt(1, categoryId);
            st2.setInt(2, movId);
            st2.executeUpdate();
        }
    }

    public void deleteMovie(int movieId) throws SQLException
    {
        String sql = "DELETE FROM Movie WHERE id =?;";
        String sql2 = "DELETE FROM CatMov WHERE MovieId =?";
        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st2 = con.prepareStatement(sql2);
            st2.setInt(1, movieId);
            st2.executeUpdate();

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, movieId);
            st.executeUpdate();
        }
    }

    public void setPRateMovie(int movieId, double rating) throws SQLException
    {
        String sql = "UPDATE Movie set personalRating = ? where id = ?;";
        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);
            st.setDouble(1, rating);
            st.setDouble(2, movieId);
            st.executeUpdate();
        }
    }

    public void setLastView(int movieId, String dayWatched) throws SQLException
    {
        String sql = "UPDATE Movie set lastView = ? where id = ?;";
        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, dayWatched);
            st.setDouble(2, movieId);
            st.executeUpdate();
        }
    }

    public List<Movie> getAllMoviesInACategory(int categoryId) throws SQLException
    {
        List<Movie> Movies = new ArrayList<>();
        String sql = "SELECT * FROM CatMov "
                + "LEFT JOIN Movie ON CatMov.MovieId=Movie.id "
                + "WHERE CategoryId = ?;";
        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double rating = rs.getDouble("rating");
                String filePath = rs.getString("filePath");
                String lastview = rs.getString("lastView");
                double pRating = rs.getInt("personalRating");
                Movie movie = new Movie(id, name, rating, filePath, lastview, pRating);
                Movies.add(movie);
            }
            return Movies;
        }

    }
}
