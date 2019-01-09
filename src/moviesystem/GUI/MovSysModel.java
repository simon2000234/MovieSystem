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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviesystem.BE.Category;
import moviesystem.BE.Movie;
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

    public ObservableList<Category> getCategories()
    {
        return categories;
    }

    public ObservableList<Category> getActiveCatFilter()
    {
        return activeFilterCat;
    }

    public void clearFilter()
    {
        filterCat.clear();
        activeFilterCat.clear();
    }

    public Category addCatToFilter(Category category)
    {
        filterCat.add(category);
        activeFilterCat.add(category);
        return category;
    }

    public ArrayList<Category> getCatFilter()
    {
        return filterCat;
    }

    public ArrayList<Category> getCatSelect()
    {
        return catSelecter;
    }

    public void createCategory(String name)
    {
        Category newCategory = msm.createCategory(name);
        categories.add(newCategory);
    }

    public void deleteCategory(Category category)
    {
        categories.remove(category);
        msm.removeCategory(category.getCategoryName());
    }

    public Category getSelectedCategory()
    {
        return selectedCategory;
    }

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

}
