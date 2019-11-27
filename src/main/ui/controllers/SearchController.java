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
import model.TimeStamp;
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

    //REQUIRES:
    //MODIFIES: View
    //EFFECTS: sets up TableView of foods
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

    //MODIFIES: View
    //EFFECTS: updates TableView with search results
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

    //MODIFIES: MainUIController
    //EFFECTS: saves the food added
    public void onAddFoodClicked() {
        try {
            NutrionixFoodResult result;
            result = (NutrionixFoodResult) searchResultsTableView.getSelectionModel().getSelectedItem();
            if (result != null) {
                SimpleFood food = new SimpleFood(result.getFoodName(), TimeStamp.getCurrentDate(),
                        Integer.parseInt(result.getCalories()), Integer.parseInt(mainref.txtFoodQuantity.getText()));
                mainref.getPerson().addFood(food);
                mainref.updateListViewFoodAdded(food);
                mainref.updateCurrentCaloriesLabel();
                Stage stage = (Stage) txtSearchTerm.getScene().getWindow();
                // do what you have to do
                stage.close();
            }
        } catch (Exception e) {
            MainUIController.numberFormatAlert();
        }
    }

    public void setMainUIController(MainUIController controller) {
        this.mainref = controller;
    }
}
