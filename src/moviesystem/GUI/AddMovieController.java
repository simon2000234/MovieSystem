/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private MovSysModel movieModel;
    private MovSysManager movSysManager;
    private String filepath;

 
    @FXML
    private TextField txtMovieName;
    @FXML
    private TextField txtRating;
    private ListView<Category> lstCategory;
    @FXML
    private Button BtnFilePicker;
    @FXML
    private TextField txtFilePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        movieModel = new MovSysModel();
       lstCategory.setItems(movieModel.getCategories());

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
        try
        {
            movSysManager.createMovie(txtMovieName.getText(), Integer.parseInt(txtRating.getText()), filepath ,msmodel.getSelectedCategory().getCategoryId());
        } catch (SQLException ex)
        {
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleChanelMovie(ActionEvent event)
    {
        Stage stage = (Stage) txtRating.getScene().getWindow();
        stage.close();
    }
}
