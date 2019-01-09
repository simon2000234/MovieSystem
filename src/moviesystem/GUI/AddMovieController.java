/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import moviesystem.BE.Category;
import moviesystem.BLL.MovSysManager;

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
    @FXML
    private TextField txtRating;
    @FXML
    private Button FilePickerBtn;
    @FXML
    private ListView<Category> lstCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      
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

    @FXML
    private void BtnGetCategory(ActionEvent event)
    {
         lstCategory.setItems(msmodel.getCategories());
    }
 
}
