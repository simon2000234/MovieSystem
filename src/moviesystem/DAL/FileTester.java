/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.DAL;

import java.sql.SQLException;
import java.util.List;
import moviesystem.BE.Category;
import moviesystem.BE.Movie;
            
/**
 *
 * @author Richart hansen
 */
public class FileTester
{

    public static void main(String[] args) throws SQLException
    {
        CategoryDAO catDAO = new CategoryDAO();
        MovieDAO movieDAO = new MovieDAO();
    }
}
