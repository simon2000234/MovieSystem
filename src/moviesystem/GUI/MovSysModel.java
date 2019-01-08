/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

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

    public MovSysModel()
    {
        this.msm = new MovSysManager();
        this.movies = FXCollections.observableArrayList();
//        movies.addAll()
        this.categories = FXCollections.observableArrayList();
        categories.addAll(msm.getAllCategories());
    }

    public ObservableList<Category> getCategories()
    {
        return categories;
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

}
