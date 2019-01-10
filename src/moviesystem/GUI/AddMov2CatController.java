/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import moviesystem.BE.Category;
import moviesystem.BE.Movie;

/**
 * FXML Controller class
 *
 * @author Melchertsen
 */
public class AddMov2CatController implements Initializable
{

    @FXML
    private ListView<Movie> lstMov;
    @FXML
    private ListView<Category> lstCat;
    @FXML
    private Label txtHidden;
    private MovSysModel msmodel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lstCat.setItems(msmodel.getCategories());
        try
        {
            lstMov.setItems(msmodel.getAllMoviesInACategory(1018));
        } catch (SQLException ex)
        {
            //yolo
        }
    }

    @FXML
    private void HandelClickOnMov(MouseEvent event)
    {
    }

    @FXML
    private void handleClickOnCat(MouseEvent event)
    {
    }

    @FXML
    private void handelConfirm(ActionEvent event)
    {
    }

    @FXML
    private void handelCancel(ActionEvent event)
    {
    }

    public void setMsmodel(MovSysModel msmodel)
    {
        this.msmodel = msmodel;
    }
}
