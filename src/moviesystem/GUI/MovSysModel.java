/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviesystem.BE.Category;
import moviesystem.BE.Movie;
import moviesystem.BE.SearchObject;
import moviesystem.BLL.MovSysManager;

/**
 *
 * @author Caspe
 */
public class MovSysModel
{

    private MovSysManager msm;
    private ObservableList<Movie> movies;
    private ObservableList<Category> categories;

    private Category selectedCategory;
    private Movie selectedMovie;
    private ArrayList<Category> catSelecter;
    private ArrayList<Category> filterCat;
    private ObservableList<Category> activeFilterCat;
    private Movie lastClickedMovie;

    public MovSysModel()
    {
        this.msm = new MovSysManager();
        this.movies = FXCollections.observableArrayList();
        this.categories = FXCollections.observableArrayList();
        categories.addAll(msm.getAllCategories());
        this.catSelecter = new ArrayList<Category>();
        catSelecter.addAll(msm.getAllCategories());
        this.filterCat = new ArrayList<Category>();
        this.activeFilterCat = FXCollections.observableArrayList();
        activeFilterCat.addAll(getCatFilter());
    }

    /**
     * 
     * @return an observablelist of all the categories 
     */
    public ObservableList<Category> getCategories()
    {
        return categories;
    }

    /**
     * 
     * @return an observablelist of the categories that are currently added to the filter
     */
    public ObservableList<Category> getActiveCatFilter()
    {
        return activeFilterCat;
    }

    /**
     * clears both the observablelist and arraylist of the filtered categories.
     */
    public void clearFilter()
    {
        filterCat.clear();
        activeFilterCat.clear();
    }

    /**
     * Adds a chosen category to both the arraylist and observablelist of the
     * filtered categories.
     * @param category
     * @return the chosen category.
     */
    public Category addCatToFilter(Category category)
    {
        filterCat.add(category);
        activeFilterCat.add(category);
        return category;
    }

    /**
     * 
     * @return the arraylist of filtered categories used to create searches. 
     */
    public ArrayList<Category> getCatFilter()
    {
        return filterCat;
    }

    /**
     * gets the categories for the box where one can select categories to add to
     * the filter
     * @return arraylist of selectable categories.
     */
    public ArrayList<Category> getCatSelect()
    {
        return catSelecter;
    }

    /**
     * Creates a category with the given name.
     * @param name 
     */
    public void createCategory(String name)
    {
        Category newCategory = msm.createCategory(name);
        categories.add(newCategory);
    }

    /**
     * Deletes the category given as a parameter from the database.
     * @param category 
     */
    public void deleteCategory(Category category)
    {
        if (category.getCategoryId() == 1018)
        {
            return;
        }
        categories.remove(category);
        msm.removeCategory(category.getCategoryName());
    }

    /**
     * 
     * @return the most recently selected category by the user. 
     */
    public Category getSelectedCategory()
    {
        return selectedCategory;
    }
    

    /**
     * Sets the selectedCategory variable to the category most recently selected
     * by the user.
     * @param selectedCategory 
     */
    public void setSelectedCategory(Category selectedCategory)
    {
        this.selectedCategory = selectedCategory;
    }

    public ObservableList<Movie> getAllMoviesInACategory(int categoryId) throws SQLException
    {
        movies.clear();
        movies.addAll(msm.getAllMoviesInACategory(categoryId));
        return movies;
    }

    public void PlayMovie(Movie movie)
    {
        try
        {
            Desktop.getDesktop().open(new File(movie.getFilePath()));
        } catch (IOException ex)
        {
            //northing dab dab
        }
    }

    public Movie getLastClickedMovie()
    {
        return lastClickedMovie;
    }

    public void setLastClickedMovie(Movie lastClickedMovie)
    {
        this.lastClickedMovie = lastClickedMovie;
    }

    public void setLastView(int movieId, String dayWatched)
    {
        try
        {
            msm.setLastView(movieId, dayWatched);
        } catch (SQLException ex)
        {
            //yolo
        }
    }

    public void setPRateMovie(int movieId, double rating)
    {
        try
        {
            msm.setPRateMovie(movieId, rating);
        } catch (SQLException ex)
        {
            //We are the Champions
        }
    }

    /**
     * Creates a SearchObject with all the given parameters.
     * @param searchString
     * @param imdbRating
     * @param personalRating
     * @param chosenCategories
     * @return the created SearchObject
     */
    public SearchObject search(String searchString, double imdbRating, double personalRating, ArrayList<Category> chosenCategories)
    {
        SearchObject FilterSearch;
        FilterSearch = new SearchObject(searchString, imdbRating, personalRating, chosenCategories);
        return FilterSearch;
    }

    /**
     * Uses the given SearchObject to created a list of movies using the method
     * in the bll layer. Then creates an ObservableList of the filtered movie list.
     * @param search
     * @return the ObservableList of the filtered movies.
     */
    public ObservableList<Movie> getSearch(SearchObject search)
    {
        ObservableList<Movie> theSearch;
        theSearch = FXCollections.observableArrayList();
        try
        {
            theSearch.addAll(msm.searchMovies(search));
        } catch (SQLException ex)
        {
            Logger.getLogger(MovSysModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theSearch;
        
    }
    public void addMovieToCat(int movieId, int catId) throws SQLException
    {
        msm.addMovieToCat(movieId, catId);
    }


    public String pickFile()
    {
        return msm.pickFile();
    }

    public void createMovie(String name, double rating, String filePath)
    {
        try
        {
            msm.createMovie(name, rating, filePath);
        } catch (SQLException ex)
        {
            //dab dab
        }
    }
    
    public void deleteMovie(int movieId)
    {
        try
        {
            msm.deleteMovie(movieId);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovSysModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public Movie getSelectedMovie()
    {
        return selectedMovie;
    }
 
 public void setSelectedMovie(Movie selectedMovie)
    {
        this.selectedMovie = selectedMovie;
    }

     public void removieMovieFromCategory(int movieId, int categoryId)
     {
        try
        {
            msm.removieMovieFromCategory(movieId, categoryId);
        } catch (SQLException ex)
        {
            //dab
        }
     }

}
