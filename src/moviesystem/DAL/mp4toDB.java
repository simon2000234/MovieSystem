/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.DAL;

import com.sun.media.sound.InvalidDataException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import moviesystem.BE.Movie;

/**
 *
 * @author andre
 */
public class mp4toDB
{

    private DBConnectionProvider dbConnect;

    public mp4toDB()
    {
        dbConnect = new DBConnectionProvider();
    }
    
    

}
