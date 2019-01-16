/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.BE;

/**
 *
 * @author Melchertsen
 */
public class Movie
{

    private int id;
    private String name;
    private double rating; //imdb rating
    private String filePath;
    private String lastview;
    private double pRating; //personal rating   

    public Movie(int id, String name, double rating, String filePath, String lastview, double pRating)
    {
        this.id=id;
        this.name=name;
        this.rating=rating;
        this.filePath=filePath;
        this.lastview=lastview;
        this.pRating=pRating;
        
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getLastview()
    {
        return lastview;
    }

    public void setLastview(String lastview)
    {
        this.lastview = lastview;
    }

    public double getpRating()
    {
        return pRating;
    }

    public void setpRating(double pRating)
    {
        this.pRating = pRating;
    }

    @Override
    public String toString()
    {
        return "" + name + ", Imdb rating: " + rating;
    }

    
}
