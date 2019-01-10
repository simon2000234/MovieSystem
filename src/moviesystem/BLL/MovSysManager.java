/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.BLL;

import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.MediaPlayer;
import moviesystem.BE.Category;
import moviesystem.BE.Movie;
import moviesystem.BE.SearchObject;
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
        mp4 = new mp4toDB();
    }

    public void createMovie(String name, double rating, String filePath) throws SQLException
    {
        movDAO.createMovie(name, rating, filePath);
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

    public List<Movie> searchMovies(SearchObject searchObject) throws SQLException
    {
        List<Movie> temp = new ArrayList<Movie>();
        List<Movie> theSearch = new ArrayList<Movie>();
        List<Integer> idCheck = new ArrayList<Integer>();

        for (Category selectedCategory : searchObject.getSelectedCategories())
        {
            temp.addAll(movDAO.getAllMoviesInACategory(selectedCategory.getCategoryId()));
        }

        double minImdb = searchObject.getMinImdbRating();
        BigDecimal minImdbRating = new BigDecimal(searchObject.getMinImdbRating());
        BigDecimal minPersonalRating = new BigDecimal(searchObject.getMinPersonalRating());

        for (Movie movie : temp)
        {
            BigDecimal movieRating = new BigDecimal(movie.getRating());
            BigDecimal moviePrating = new BigDecimal(movie.getpRating());
            if (movie.getName().toLowerCase().contains(searchObject.getSearchString().toLowerCase())
                    && idCheck.contains(movie.getId()) != true
                    && minImdbRating.compareTo(movieRating) <= 0
                    && minPersonalRating.compareTo(moviePrating) <= 0)
            {
                theSearch.add(movie);
                idCheck.add(movie.getId());
            } else
            {
                //nothing
            }
        }
        return theSearch;
    }

    public String pickFile()

    {
        return mp4.pickFile();

    }
    

    public void addMovieToCat(int movieId, int catId) throws SQLException
    {
        movDAO.addMovieToCat(movieId, catId);

    }
}
