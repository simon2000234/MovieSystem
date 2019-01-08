/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class AddMovieController implements Initializable
{
    
       private MovSysModel msmodel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }  
    
    
      public MovSysModel getMsmodel()
    {
        return msmodel;
    }

    public void setMsmodel(MovSysModel msmodel)
    {
        this.msmodel = msmodel;
    }
    
}
