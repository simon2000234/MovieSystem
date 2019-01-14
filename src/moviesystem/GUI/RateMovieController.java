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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Melchertsen
 */
public class RateMovieController implements Initializable
{

    private MovSysModel msmodel;
    @FXML
    private TextField txtRating;
    @FXML
    private Text txtMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * sets the model  
     * @param msmodel the model
     */
    public void setMsmodel(MovSysModel msmodel)
    {
        this.msmodel = msmodel;
    }

    @FXML
    /**
     * sets a personal rating of the movie in the database
     * the rating must be between 0 and 10
     */
    private void handelConfirm(ActionEvent event)
    {
        String inputText = txtRating.getText();
        try
        {
            double inputDouble = Double.valueOf(txtRating.getText());
            msmodel.setPRateMovie(msmodel.getLastClickedMovie().getId(), inputDouble);
            Stage stage = (Stage) txtRating.getScene().getWindow();
            stage.close();
        } catch (NullPointerException | NumberFormatException ex)
        {
            txtMain.setText("Please only nummbers from 0 to 10... dum dum");
        }
    }

    @FXML
    /**
     * closes the window
     */
    private void handleCancel(ActionEvent event)
    {
        Stage stage = (Stage) txtRating.getScene().getWindow();
        stage.close();
    }
}
