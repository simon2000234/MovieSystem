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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Caspe
 */
public class AddCategoryWindowController implements Initializable
{

    @FXML
    private TextField txtAddCategoryName;
    @FXML
    private Button btnSaveButton;
    @FXML
    private Button btnAddCategoryClose;
    private MovSysModel msmodel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }    

    @FXML
    /**
     * adds a new category to the database
     */
    private void handleSaveButton(ActionEvent event)
    {
        msmodel.createCategory(txtAddCategoryName.getText());
    }

    @FXML
    /**
     * Closes the window
     */
    private void handleCloseButton(ActionEvent event)
    {
        Stage stage = (Stage) btnAddCategoryClose.getScene().getWindow();
        stage.close();
    }

    /**
     * gets the model
     * @return the model
     */
    public MovSysModel getMsmodel()
    {
        return msmodel;
    }

    /**
     * sets the model
     * @param msmodel 
     */
    public void setMsmodel(MovSysModel msmodel)
    {
        this.msmodel = msmodel;
    }
    
    
    
}
