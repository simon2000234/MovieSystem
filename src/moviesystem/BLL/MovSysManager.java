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
        movDAO.createMovie(name, rating, filePath);
    }

    /**
     * Deletes a movie from the datebase, both the movie itself and remove it
     * from the categorys it is in
     *
     * @param movieId the id of the movie that you wish to delete
     * @throws SQLException
     */
    public void deleteMovie(int movieId) throws SQLException
    {
        movDAO.deleteMovie(movieId);
    }

    /**
     * This allows you to add a personal rating to the movies bassed on how much
     * you like them
     *
     * @param movieId the id of the movie that you wish to rate
     * @param rating the rating that you are giving the movie, must be between 0
     * and 10
     * @throws SQLException
     */
    public void setPRateMovie(int movieId, double rating) throws SQLException
    {
        movDAO.setPRateMovie(movieId, rating);
    }

    /**
     * This saves the day that you last saw the movie
     *
     * @param movieId the id of the movie that you watched
     * @param dayWatched the day you watched it on
     * @throws SQLException
     */
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

    /**
     * This will return a list of all the movies in a specific category
     *
     * @param categoryId the id of the category that you want all movies from
     * @return a list of all movies in a category
     * @throws SQLException
     */
    public List<Movie> getAllMoviesInACategory(int categoryId) throws SQLException
    {
        return movDAO.getAllMoviesInACategory(categoryId);
    }
    
    /**
     * Uses a searchObject which contains the information that a user provided
     * in a filter. First creates a temporary list of all movies with the desired categories,
     * then adds every movie that meets the required rating, personal rating and title/text
     * to a list of movies that is then returned. Duplicates are handled by checking
     * the movie ID, so a movie with the same movie ID won't appear multiple times on the list.
     * @param searchObject
     * @return a list of movies meeting the requirements of the filter.
     * @throws SQLException 
     */
    public List<Movie> searchMovies(SearchObject searchObject) throws SQLException
    {
        List<Movie> temp = new ArrayList<Movie>();
        List<Movie> theSearch = new ArrayList<Movie>();
        List<Integer> idCheck = new ArrayList<Integer>();

        for (Category selectedCategory : searchObject.getSelectedCategories())
        {
            temp.addAll(movDAO.getAllMoviesInACategory(selectedCategory.getCategoryId()));
        }

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

    
    /**
     * 
     * @return
     * sender den valget file vider 
     * til GUI.
     */
     
    public String pickFile()

    {
        return mp4.pickFile();
    }

    /**
     * Adds a movie to a categpry
     *
     * @param movieId the id of the movie that you want to add
     * @param catId the id of the category that you wish to add the movie too
     * @throws SQLException
     */
    public void addMovieToCat(int movieId, int catId) throws SQLException
    {
        movDAO.addMovieToCat(movieId, catId);

    }

    /**
     * removes a movie from category
     *
     * @param movieId the id of the movie that you wish to remove
     * @param categoryId the id of the category that you wish to remove it from
     * @throws SQLException
     */
    public void removieMovieFromCategory(int movieId, int categoryId) throws SQLException
    {
        movDAO.removieMovieFromCategory(movieId, categoryId);
    }
}
