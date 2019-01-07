/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void createMovie(String name, double rating, String filePath) throws SQLException
    {
        String sql = "INSERT INTO Movie(name, rating, filePath) VALUES (?,?,?);";
        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setDouble(2, rating);
            st.setString(3, filePath);
            st.executeUpdate();
        }
    }

    public void deleteMovie(int movieId) throws SQLException
    {
        String sql = "DELETE FROM Movie WHERE id =?;";
        try (Connection con = dbConnect.getConnection())
        {
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
    public void setLastView(int movieId, int daysSinceLastWatched) throws SQLException
    {
         String sql = "UPDATE Movie set lastView = ? where id = ?;";
         try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);
            st.setDouble(1, daysSinceLastWatched);
            st.setDouble(2, movieId);
            st.executeUpdate();
        }
    }
}
