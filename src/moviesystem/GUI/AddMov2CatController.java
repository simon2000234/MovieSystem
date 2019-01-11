/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    private Label txtHidden;
    private MovSysModel msmodel;
    private Movie selectedMovie;
    private Category selectedCategory;
    private boolean isMovieInCat;
    @FXML
    private ListView<Movie> lstOfMovies;
    @FXML
    private ListView<Category> lstCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    @FXML
    private void HandelClickOnMov(MouseEvent event)
    {
        selectedMovie = lstOfMovies.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleClickOnCat(MouseEvent event)
    {
        selectedCategory = lstCategory.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handelConfirm(ActionEvent event) throws SQLException
    {
        if (selectedCategory == null || selectedMovie == null)
        {
            txtHidden.setText("You need to chose a category and a movie");
            return;
        }
        ObservableList<Movie> theList = msmodel.getAllMoviesInACategory(selectedCategory.getCategoryId());
        isMovieInCat = false;
        for (Movie movie : theList)
        {
            if (movie == selectedMovie)
            {
                isMovieInCat = true;
            }
        }
        if (isMovieInCat == true)
        {
            txtHidden.setText("The Movie is already in the Category");
            return;
        }
        msmodel.addMovieToCat(selectedMovie.getId(), selectedCategory.getCategoryId());
        txtHidden.setText("Movie added to category");
    }

    @FXML
    private void handelCancel(ActionEvent event)
    {
        Stage stage = (Stage) txtHidden.getScene().getWindow();
        stage.close();
    }

    public void setMsmodel(MovSysModel msmodel)
    {
        this.msmodel = msmodel;
        lstCategory.setItems(this.msmodel.getCategories());
        try
        {
            lstOfMovies.setItems(this.msmodel.getAllMoviesInACategory(1018));
        } catch (SQLException ex)
        {
            //yolo
        }
    }
}
