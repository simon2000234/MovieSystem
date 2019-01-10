/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import moviesystem.BE.Category;
import moviesystem.BE.Movie;

/**
 *
 * @author Melchertsen
 */
public class FXMLDocumentController implements Initializable
{

    private Label label;
    @FXML
    private ListView<Category> lstcat;
    @FXML
    private ListView<Movie> lstmovie;
    private MovSysModel msmodel;
    @FXML
    private TextField txtfilter;
    @FXML
    private ComboBox<Category> cmbCategorySelecter;
    @FXML
    private ListView<Category> lstActiveCatFilter;
    @FXML
    private Button btnClearFilter;
    @FXML
    private TextField txtRatingIMDB;
    @FXML
    private TextField txtPersonalRating;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.msmodel = new MovSysModel();
        lstcat.setItems(msmodel.getCategories());
        cmbCategorySelecter.getItems().addAll(msmodel.getCatSelect());
        lstActiveCatFilter.setItems(msmodel.getActiveCatFilter());
    }

    @FXML
    private void handleaddmovie(ActionEvent event)
    {
        Parent root;
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("moviesystem/GUI/addMovie.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add a new Movie");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();

            AddMovieController AddMovieController = loader.getController();
            AddMovieController.setMsmodel(msmodel);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    private void handledeletemovie(ActionEvent event)
    {
    }

    @FXML
    private void handleratemovie(ActionEvent event) throws IOException
    {

        if (msmodel.getLastClickedMovie() == null)
        {
            System.out.println("Please Sellected a movie to rate");
            return;
        } else
        {
            Parent root;
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("moviesystem/GUI/RateMovie.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Rate a Moive");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();

                RateMovieController rmController = loader.getController();
                rmController.setMsmodel(msmodel);

            } catch (IOException ex)
            {
                //something
            }
        }
    }

    @FXML
    private void handleaddcat(ActionEvent event) throws IOException
    {

        Parent root;
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("moviesystem/GUI/AddCategoryWindow.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add a new Category");
            stage.setScene(new Scene(root, 300, 250));
            stage.show();

            AddCategoryWindowController addCategoryWindowController = loader.getController();
            addCategoryWindowController.setMsmodel(msmodel);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    private void handledeletecat(ActionEvent event)
    {
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION, "Delete: " + msmodel.getSelectedCategory().getCategoryName() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        confirmDelete.setTitle("Delete Category");
        confirmDelete.setHeaderText("Are you sure?");
        confirmDelete.showAndWait();

        if (confirmDelete.getResult() == ButtonType.YES)
        {
            msmodel.deleteCategory(msmodel.getSelectedCategory());
        }
    }

    @FXML
    private void handleClickOnCategory(MouseEvent event) throws SQLException
    {
        Category currentcat = lstcat.getSelectionModel().getSelectedItem();
        msmodel.setSelectedCategory(currentcat);
        System.out.println("" + msmodel.getSelectedCategory().getCategoryName());
        lstmovie.setItems(msmodel.getAllMoviesInACategory(currentcat.getCategoryId()));
    }

    @FXML
    private void handletxtfilter(ActionEvent event)
    {
    }

    @FXML
    private void handleCategorySelect(ActionEvent event)
    {
        if (msmodel.getCatFilter().contains(cmbCategorySelecter.getSelectionModel().getSelectedItem()))
        {
            //do nothing
        } else
        {
            msmodel.addCatToFilter(cmbCategorySelecter.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void handlePlayMovie(ActionEvent event)
    {
        if (msmodel.getLastClickedMovie() == null)
        {
            System.out.println("No movie selected dum dum");
        } else
        {
            msmodel.PlayMovie(msmodel.getLastClickedMovie());
            String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
            msmodel.setLastView(msmodel.getLastClickedMovie().getId(), timeStamp);
        }
    }

    @FXML
    private void handleClearFilterButton(ActionEvent event)
    {
        msmodel.clearFilter();
    }

    @FXML
    private void handleClickOnMovie(MouseEvent event)
    {
        Movie lastClickedMovie = lstmovie.getSelectionModel().getSelectedItem();
        msmodel.setLastClickedMovie(lastClickedMovie);
    }
}
