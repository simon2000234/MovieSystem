/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class AddMovieController implements Initializable
{
    
       private MovSysModel msmodel;
    @FXML
    private TextField txtMovieName;
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

    @FXML
    private void handleAddMovie(ActionEvent event)
    {
       
    }

    @FXML
    private void handleChanelMovie(ActionEvent event)
    {
    }
    
}
