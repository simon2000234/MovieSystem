/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.BE;

/**
 *
 * @author andre
 */
public class Category
{
 
    private int categoryId;
     private String CategoryName;
    /**
     * Get the value of categoryId
     *
     * @return the value of categoryId
     */
    public int getCategoryId()
    {
        return categoryId;
    }

      

    /**
     * Get the value of CategoryName
     *
     * @return the value of CategoryName
     */
    public String getCategoryName()
    {
        return CategoryName;
    }

    /**
     * Set the value of CategoryName
     *
     * @param CategoryName new value of CategoryName
     */
    public void setCategoryName(String CategoryName)
    {
        this.CategoryName = CategoryName;
    }


    
}
