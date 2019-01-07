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
import moviesystem.BE.Category;

/**
 *
 * @author Caspe
 */
public class CategoryDAO
{

    private DBConnectionProvider dbConnect;

    public CategoryDAO()
    {
        dbConnect = new DBConnectionProvider();
    }

    public void createCategory(String name)
    {
        String sql = "INSERT INTO Category(name) VALUES(?);";

        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, name);

            int rowsAffected = st.executeUpdate();

        } catch (SQLException ex)
        {
            //nothing
        }
    }

    public void removeCategory(String name)
    {
        String sql = "DELETE FROM Category WHERE name=" + name + ";";

        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException ex)
        {
            //nothing
        }
    }

    public List getAllCategories()
    {
        String sql = "SELECT * FROM Category";
        
        ArrayList<Category> allCategories = new ArrayList<>();

        try (Connection con = dbConnect.getConnection())
        {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getNString("name");
                allCategories.add(new Category(id, name));
                
            }
        } catch (SQLException ex)
        {
            //nothing
        }
        return allCategories;
    }
}
