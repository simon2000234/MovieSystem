/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.DAL;

import java.sql.SQLException;

/**
 *
 * @author Richart hansen
 */
public class FileTester
{

    public static void main(String[] args) throws SQLException
    {
        MovieDAO movieDAO = new MovieDAO();
//        movieDAO.createMovie("Sunset", 5.5, "asd"); 
//movieDAO.pRateMovie(4, 7.2);
movieDAO.setLastView(4, 365);
    }
}
