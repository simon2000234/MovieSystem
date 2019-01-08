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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import moviesystem.BE.Category;

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
    private ListView<?> lstmovie;
    private MovSysModel msmodel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.msmodel = new MovSysModel();
        lstcat.setItems(msmodel.getCategories());
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
    }

    @FXML
    private void handleClickOnCategory(MouseEvent event)
    {
        Category currentcat = lstcat.getSelectionModel().getSelectedItem();
        msmodel.setSelectedCategory(currentcat);
        System.out.println("" + msmodel.getSelectedCategory().getCategoryName());
    }
}
