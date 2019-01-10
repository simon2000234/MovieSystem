/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.BLL;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import moviesystem.BE.Category;
import moviesystem.BE.Movie;
import moviesystem.DAL.CategoryDAO;
import moviesystem.DAL.MovieDAO;
import moviesystem.DAL.mp4toDB;

/**
 *
 * @author Melchertsen
 */
public class MovSysManager
{

    private MediaPlayer mediaPlayer;
    private CategoryDAO catDAO;
    private MovieDAO movDAO;
    private mp4toDB mp4;

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

    public void setLastView(int movieId, String dayWatched) throws SQLException
    {
        movDAO.setLastView(movieId, dayWatched);
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

    public String getFileName()
    {
        mp4 = new mp4toDB();
        String fileName = mp4.pickFile();

        return fileName;

    }
}
