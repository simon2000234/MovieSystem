/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.BLL;

import java.sql.SQLException;
import java.util.List;
import moviesystem.BE.Category;
import moviesystem.BE.Movie;
import moviesystem.DAL.CategoryDAO;
import moviesystem.DAL.MovieDAO;

/**
 *
 * @author Melchertsen
 */
public class MovSysManager
{

    private CategoryDAO catDAO;
    private MovieDAO movDAO;

    public MovSysManager()
    {
        catDAO = new CategoryDAO();
        movDAO = new MovieDAO();
    }

    public void createMovie(String name, double rating, String filePath, int categoryId) throws SQLException
    {
        movDAO.createMovie(name, rating, filePath, categoryId);
    }

    public void deleteMovie(int movieId) throws SQLException
    {
        movDAO.deleteMovie(movieId);
    }

    public void setPRateMovie(int movieId, double rating) throws SQLException
    {
        movDAO.setPRateMovie(movieId, rating);
    }

    public void setLastView(int movieId, int daysSinceLastWatched) throws SQLException
    {
        movDAO.setLastView(movieId, daysSinceLastWatched);
    }

    public Category createCategory(String name)
    {
        Category newCat;
        newCat = catDAO.createCategory(name);
        return newCat;
    }

    public void removeCategory(String name)
    {
        catDAO.removeCategory(name);
    }

    public List<Category> getAllCategories()
    {
        return catDAO.getAllCategories();
    }

    public List<Movie> getAllMoviesInACategory(int categoryId) throws SQLException
    {
        return movDAO.getAllMoviesInACategory(categoryId);
    }

}
