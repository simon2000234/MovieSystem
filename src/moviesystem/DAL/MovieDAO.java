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

    /**
     * Creates a movie in the database
     *
     * @param name The name of the movie
     * @param rating The imdb rating of the movie, must be between 0 and 10
     * @param filePath the path to the mp4 file on the computer
     * @throws SQLException
     */
    public void createMovie(String name, double rating, String filePath) throws SQLException
    {
        if (rating < 0.0 || rating > 10.0)
        {
            System.out.println("kun 0.0 til 10.0 dit fjols");
            return;
        }
        
        List<Movie> allMovies = getAllMoviesInACategory(1018);
        
        for (Movie allMovy : allMovies)
        {
            if(allMovy.getName().toLowerCase().equals(name.toLowerCase()))
            {
                System.out.println("There is already a movie with that name");
                return;
            }
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
            st2.setInt(1, 1018);
            st2.setInt(2, movId);
            st2.executeUpdate();
        }
    }

    /**
     * Adds a movie to a categpry
     * @param movieId the id of the movie that you want to add
     * @param catId the id of the category that you wish to add the movie too
     * @throws SQLException
     */
    public void addMovieToCat(int movieId, int catId) throws SQLException
    {
        String sql = "INSERT INTO CatMov(CategoryId, MovieId) VALUES (?,?);";
         try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, catId);
            st.setInt(2, movieId);
            st.executeUpdate();
        }
    }

    /**
     * Deletes a movie from the datebase, both the movie itself and
     * remove it from the categorys it is in
     * @param movieId the id of the movie that you wish to delete
     * @throws SQLException
     */
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

    /**
     * This allows you to add a personal rating to the movies
     * bassed on how much you like them
     * @param movieId the id of the movie that you wish to rate
     * @param rating the rating that you are giving the movie,
     * must be between 0 and 10
     * @throws SQLException
     */
    public void setPRateMovie(int movieId, double rating) throws SQLException
    {
        if (rating < 0.0 || rating > 10.0)
        {
            System.out.println("kun 0.0 til 10.0 dit fjols");
            return;
        }
        String sql = "UPDATE Movie set personalRating = ? where id = ?;";
        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);
            st.setDouble(1, rating);
            st.setDouble(2, movieId);
            st.executeUpdate();
        }
    }

    /**
     * This saves the day that you last saw the movie
     * @param movieId the id of the movie that you watched
     * @param dayWatched the day you watched it on
     * @throws SQLException
     */
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

    /**
     * This will return a list of all the movies in a specific category
     * @param categoryId the id of the category that you want all movies from
     * @return a list of all movies in a category
     * @throws SQLException
     */
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
                double pRating = rs.getDouble("personalRating");
                Movie movie = new Movie(id, name, rating, filePath, lastview, pRating);
                Movies.add(movie);
            }
            return Movies;
        }

    }
    
    /**
     * removes a movie from category
     * @param movieId the id of the movie that you wish to remove
     * @param categoryId the id of the category that you wish to remove it from
     * @throws SQLException
     */
    public void removieMovieFromCategory(int movieId, int categoryId) throws SQLException
    {
        String sql = "DELETE FROM CatMov WHERE MovieId =? AND CategoryId =?";
        if (categoryId == 1018)
        {
            System.out.println("nope");
            return;
        }
        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, movieId);
            st.setInt(2, categoryId);
            st.executeUpdate();
        }
    }
}
