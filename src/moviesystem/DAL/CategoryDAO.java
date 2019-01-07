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

    /**
     * Creates a category in the SQL database with the given parameter as its name
     * If a category with the given name already exists, another category with
     * the same name will NOT be created.
     * @param name 
     */
    public void createCategory(String name)
    {
        for (Category cat : getAllCategories())
        {
            if (cat.getCategoryName().equals(name))
            {
                System.out.println("FAILED: A category with this name already exists");
                return;
            }
        }
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

    /**
     * Removes an existing category with the given name.
     * @param name 
     */
    public void removeCategory(String name)
    {
        String sql = "DELETE FROM Category WHERE name='" + name + "';";

        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException ex)
        {
            //nothing
        }
    }

    /**
     * Creates a list with all categories in the database.
     * @return a list of all categories in the database.
     */
    public List<Category> getAllCategories()
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
