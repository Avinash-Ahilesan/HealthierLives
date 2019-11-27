package ui.controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.food.Food;
import model.food.Ingredient;
import model.food.MealFood;

public class AddMealFoodController {
    private MainUIController controller;
    private MealFood meal;
    public TextField txtFoodName;
    public TextField txtFoodCalories;
    public TextField txtFoodQuantity;
    public TableView tableMealContents;

    //MODIFIES: View
    //EFFECTS: sets up the TableView for the meal contents
    public void initialize() {
        TableColumn<String, Food> foodNameColumn = new TableColumn<>("Food Name");
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Food> caloriesColumn = new TableColumn<>("Calories");
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));

        TableColumn<String, Food> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("numEaten"));


        tableMealContents.getColumns().add(foodNameColumn);
        tableMealContents.getColumns().add(caloriesColumn);
        tableMealContents.getColumns().add(quantityColumn);
        tableMealContents.setPlaceholder(new Label("No foods have been added yet"));
    }

    //MODIFIES: View, this
    //EFFECTS: adds the ingredient to the tableView and also to the container
    public void onAddIngredientClicked() {
        String foodName = txtFoodName.getText();
        int foodCalories = Integer.parseInt(txtFoodCalories.getText());
        int foodQuantity = 1;
        if (!txtFoodQuantity.getText().equals("")) {
            foodQuantity = Integer.parseInt(txtFoodQuantity.getText());
        }
        Ingredient ingredient = new Ingredient(foodName, foodCalories, foodQuantity);
        tableMealContents.getItems().add(ingredient);


    }

    //MODIFIES: View
    //EFFECTS: Removes selected food from the meal
    public void onRemoveSelected() {
        Food food = (Food) tableMealContents.getSelectionModel().getSelectedItem();
        if (food != null) {
            tableMealContents.getItems().remove(food);
        }
    }

    //REQUIRES:
    //MODIFIES: View, MainUIController
    //EFFECTS: saves the meal, updates the main view,
    //         and closes the current view
    public void onSaveMeal() {
        ObservableList list = tableMealContents.getItems();
        for (Object obj : list) {
            if (obj != null) {
                Ingredient ingr = (Ingredient)obj;
                meal.addIngredient(ingr);
            }
        }
        //TODO: make the screen close
        controller.updateListViewFoodAdded(meal);
        Stage stage = (Stage) txtFoodName.getScene().getWindow();
        stage.close();
    }

    public void setMainUIController(MainUIController controller) {
        this.controller = controller;
    }

    public void setMeal(MealFood meal) {
        this.meal = meal;
    }
}
