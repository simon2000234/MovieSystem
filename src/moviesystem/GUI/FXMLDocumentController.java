/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author Melchertsen
 */
public class FXMLDocumentController implements Initializable
{
    
    private Label label;
    @FXML
    private ListView<?> lstcat;
    @FXML
    private ListView<?> lstmovie;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void handleaddmovie(ActionEvent event)
    {
    }

    @FXML
    private void handledeletemovie(ActionEvent event)
    {
    }

    @FXML
    private void handleratemovie(ActionEvent event)
    {
    }

    @FXML
    private void handleaddcat(ActionEvent event) throws IOException
    {
         
        
        
    }

    @FXML
    private void handledeletecat(ActionEvent event)
    {
    }

    
}
