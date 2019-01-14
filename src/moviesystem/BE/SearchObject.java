/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.BE;

import java.util.ArrayList;

/**
 *
 * @author Casper
 */
public class SearchObject
{

    private String searchString;
    private double minImdbRating;
    private double minPersonalRating;
    private ArrayList<Category> selectedCategories;

    public SearchObject(String searchString, double minImdbRating, double minPersonalRating, ArrayList<Category> arraylist)
    {
        this.searchString=searchString;
        this.minImdbRating=minImdbRating;
        this.minPersonalRating=minPersonalRating;
        this.selectedCategories=arraylist;     
    }

    public String getSearchString()
    {
        return searchString;
    }

    public void setSearchString(String searchString)
    {
        this.searchString = searchString;
    }

    public double getMinImdbRating()
    {
        return minImdbRating;
    }

    public void setMinImdbRating(int minImdbRating)
    {
        this.minImdbRating = minImdbRating;
    }

    public double getMinPersonalRating()
    {
        return minPersonalRating;
    }

    public void setMinPersonalRating(int minPersonalRating)
    {
        this.minPersonalRating = minPersonalRating;
    }

    public ArrayList<Category> getSelectedCategories()
    {
        return selectedCategories;
    }

    public void setSelectedCategories(ArrayList<Category> selectedCategories)
    {
        this.selectedCategories = selectedCategories;
    }
}
