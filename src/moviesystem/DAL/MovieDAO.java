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
}
