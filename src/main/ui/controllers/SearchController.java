package ui.controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Person;
import model.food.Food;
import model.food.SimpleFood;
import network.Nutrionix;
import network.NutrionixFoodResult;

import java.util.ArrayList;

public class SearchController {

    public TextField txtSearchTerm;
    public TableView searchResultsTableView;
    public ImageView imgThumb;
    public ImageView imgThumbView;
    public MainUIController mainref;
    ArrayList<NutrionixFoodResult> foodResults;
    Nutrionix api;

    public void initialize() {
        api = new Nutrionix();
        TableColumn<String, NutrionixFoodResult> foodNameColumn = new TableColumn<>("Food Name");
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));

        TableColumn<String, NutrionixFoodResult> caloriesColumn = new TableColumn<>("Calories");
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));

        searchResultsTableView.getColumns().add(foodNameColumn);
        searchResultsTableView.getColumns().add(caloriesColumn);
        searchResultsTableView.setPlaceholder(new Label("Nothing has been searched up yet"));

        /*searchResultsTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {

                    }
                });*/
    }

    public void searchForFood() {
        foodResults = api.getFoodResults(txtSearchTerm.getText());
        updateTableContents();
    }

    private void updateTableContents() {
        searchResultsTableView.getItems().clear();
        for (NutrionixFoodResult food : foodResults) {
            searchResultsTableView.getItems().add(food);
        }
    }

    public void onAddFoodClicked() {
        NutrionixFoodResult result = (NutrionixFoodResult)searchResultsTableView.getSelectionModel().getSelectedItem();
        if (result != null) {
            SimpleFood food = new SimpleFood(result.getFoodName(), mainref.getCurrentDate(),
                    Integer.parseInt(result.getCalories()), 1);
            mainref.getPerson().addFood(food);
            mainref.updateListViewFoodAdded(food);
            Stage stage = (Stage) txtSearchTerm.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }

    public void setMainUIController(MainUIController controller) {
        this.mainref = controller;
    }
}
