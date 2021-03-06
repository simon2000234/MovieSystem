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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private Button btnSearch;
    @FXML
    private TableView<Movie> tableMovie;
    @FXML
    private TableColumn<Movie, String> columnTitle;
    @FXML
    private TableColumn<Movie, Double> columnRating;
    @FXML
    private TableColumn<Movie, Double> columnPersonalRating;
    @FXML
    private TableColumn<Movie, String> columnLastViewed;
    @FXML
    private Label txtHidden;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ReminderPopup();
        //Sets all the items of the listviews and tableview and sets the columns of the tableview.
        this.msmodel = new MovSysModel();
        lstcat.setItems(msmodel.getCategories());
        cmbCategorySelecter.getItems().addAll(msmodel.getCatSelect());
        lstActiveCatFilter.setItems(msmodel.getActiveCatFilter());
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        columnPersonalRating.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getpRating()));
        columnLastViewed.setCellValueFactory(new PropertyValueFactory<>("lastview"));
        try
        {
            tableMovie.setItems(msmodel.getMovies());
            msmodel.loadAllMoviesInACategory(1018);
        } catch (SQLException ex)
        {
            //Jeg er så glad idag
        }
    }

    @FXML
    /**
     * opens a window to add movies
     */
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
    /**
     * deletes the sellected movie
     */
    private void handledeletemovie(ActionEvent event)
    {
        msmodel.deleteMovie(tableMovie.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    /**
     * opens a windows to rate the selected movie, checks to see if you have
     * selected a movie
     */
    private void handleratemovie(ActionEvent event)
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
    /**
     * Opens a new scene where a category can be created.
     */
    private void handleaddcat(ActionEvent event)
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
    /**
     * Deletes the selected category, when button is clicked an alert window
     * opens asking if you are sure you want to delete the selected category.
     */
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
    /**
     * Changes the listview for movies to show the movies that are in the
     * category you clicked on
     */
    private void handleClickOnCategory(MouseEvent event)
    {
        Category currentcat = lstcat.getSelectionModel().getSelectedItem();
        msmodel.setSelectedCategory(currentcat);
        try
        {
            if (currentcat == null)
            {
                msmodel.loadAllMoviesInACategory(1018);
            } else
            {
                msmodel.loadAllMoviesInACategory(currentcat.getCategoryId());
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handletxtfilter(ActionEvent event)
    {
        fixSearch();
    }

    @FXML
    /**
     * Adds the chosen category to the active category filter for the search.
     */
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
    /**
     * Plays the selected movie, checks to see if you have selected a movie
     */
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
    /**
     * Clears the active filter, removes the text present in the TextFields and
     * returns the tableview containing movies back to showing all movies.
     */
    private void handleClearFilterButton(ActionEvent event)
    {
        try
        {
            msmodel.clearFilter();
            txtRatingIMDB.setText("");
            txtPersonalRating.setText("");
            txtfilter.setText("");
            tableMovie.setItems(msmodel.getMovies());
            msmodel.loadAllMoviesInACategory(1018);
        } catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    /**
     * saves the movie that you last clicked on
     */
    private void handleClickOnMovie(MouseEvent event)
    {
        Movie lastClickedMovie = tableMovie.getSelectionModel().getSelectedItem();
        msmodel.setLastClickedMovie(lastClickedMovie);
    }

    @FXML
    private void handleMinRatingtxt(ActionEvent event)
    {
        fixSearch();
    }

    @FXML
    private void handleMinPersonalRatingtxt(ActionEvent event)
    {
        fixSearch();
    }

    @FXML
    private void handleSearchButton(ActionEvent event)
    {
        fixSearch();
    }

    /**
     * Fixes the search so that if nothing is entered, all movies will appear.
     * If a field is left empty, the search will use a value that allows all
     * movies to meet the requirement of the empty field, e.g. minimum rating
     * 0.0
     */
    public void fixSearch()
    {
        String searchText;
        double minImdb;
        double minPersonal;
        ArrayList<Category> catFilter = new ArrayList<Category>();
        if (txtfilter.getText().isEmpty())
        {
            searchText = "";
        } else
        {
            searchText = txtfilter.getText();
        }
        if (txtRatingIMDB.getText().isEmpty())
        {
            minImdb = 0.0;
        } else
        {
            minImdb = Double.parseDouble(txtRatingIMDB.getText());
        }
        if (txtPersonalRating.getText().isEmpty())
        {
            minPersonal = 0.0;
        } else
        {
            minPersonal = Double.parseDouble(txtPersonalRating.getText());
        }
        if (msmodel.getCatFilter().isEmpty())
        {
            catFilter.addAll(msmodel.getCategories());
        } else
        {
            catFilter.addAll(msmodel.getCatFilter());
        }
        tableMovie.setItems(msmodel.getSearch(msmodel.search(searchText, minImdb, minPersonal, catFilter)));
    }

    @FXML
    /**
     * Opens the window to add a movie to a category
     */
    private void handleAddMov2Cat(ActionEvent event)
    {
        Parent root;
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("moviesystem/GUI/AddMov2Cat.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add a Movie to a Category");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

            AddMov2CatController am2cc = loader.getController();
            am2cc.setMsmodel(msmodel);

        } catch (IOException ex)
        {
            //something
        }
    }

    @FXML
    /**
     * Removes a movie from a category, checks if you have sellected a movie and
     * a category
     */
    private void handelRemoveMovFromCat(ActionEvent event)
    {
        if (tableMovie.getSelectionModel().getSelectedItem() == null || lstcat.getSelectionModel().getSelectedItem() == null)
        {
            txtHidden.setText("Please select a Category and then a movie to remove it from the category");
        } else
        {
            msmodel.removieMovieFromCategory(tableMovie.getSelectionModel().getSelectedItem().getId(), lstcat.getSelectionModel().getSelectedItem().getCategoryId());
            try
            {
                msmodel.loadAllMoviesInACategory(lstcat.getSelectionModel().getSelectedItem().getCategoryId());
            } catch (SQLException ex)
            {

            }
        }
    }

    /**
     * Creates an alert to remind the user to remove old movies with a low
     * rating
     */
    public void ReminderPopup()
    {
        Alert popupReminder = new Alert(Alert.AlertType.INFORMATION);
        popupReminder.setHeaderText(null);
        popupReminder.setTitle("Reminder");
        popupReminder.setContentText("Remember to delete all movies below 6 personal rating that haven't been opened in the past 2 years to clear up space");
        popupReminder.showAndWait();
    }
}
