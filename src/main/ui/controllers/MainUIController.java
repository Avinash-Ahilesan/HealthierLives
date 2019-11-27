package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Person;
import model.TimeStamp;
import model.food.Food;
import model.food.MealFood;
import model.food.SimpleFood;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static model.TimeStamp.getCurrentDate;

public class MainUIController implements Observer {

    private ObservableList<Food> foodEatenOnDate;
    private Person person;
    public Button btnAddFood;
    private final String searchFoodLayout = "../searchfood.fxml";
    private final String addMealFoodLayout = "../addfood.fxml";
    public Label lblName;
    public Label lblAge;
    public Label lblTargetCalories;
    public Label lblCurrentCalories;

    public DatePicker foodDateForDisplay;
    public TimeStamp currentTimeSelected = null;
    public TextField txtFoodName;
    public TextField txtSimpleFoodCalories;
    public TextField txtFoodQuantity;
    public TableView foodEatenTableView;

    public RadioButton radioMeal;
    public RadioButton radioSimple;
    public RadioButton radioSearch;


    //TODO: only allow numbers in textfields that require it
    //MODIFIES: View
    //EFFECTS: sets view properties for table
    public void initialize() {
        radioSimple.setSelected(true);

        TableColumn<String, Food> foodNameColumn = new TableColumn<>("Food Name");
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Food> caloriesColumn = new TableColumn<>("Calories");
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));

        TableColumn<String, Food> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("numEaten"));

        foodEatenTableView.getColumns().add(foodNameColumn);
        foodEatenTableView.getColumns().add(caloriesColumn);
        foodEatenTableView.getColumns().add(quantityColumn);
        foodEatenTableView.setPlaceholder(new Label("No foods have been added yet"));

    }

    //MODIFIES:this
    //EFFECTS: Opens appropriate tab depending on selected
    //         radio box, creating food with the properties from the text fields
    public void onAddFoodClicked() {
        try {
            if (radioSimple.isSelected()) {
                createSimple();
            }
            if (radioSearch.isSelected()) {
                loadSearchScene();
            }
            if (radioMeal.isSelected()) {
                loadAddMealScene();
            }
            updateCurrentCaloriesLabel();
        } catch (NumberFormatException e) {
            numberFormatAlert();
        }

    }

    //REQUIRES:
    //MODIFIES: View, person
    //EFFECTS: removes the selected food from the tableview and person
    public void onRemoveFood() {
        Food food = (Food) foodEatenTableView.getSelectionModel().getSelectedItem();
        if (food != null) {
            foodEatenTableView.getItems().remove(food);
            person.removeFood(food);
            updateCurrentCaloriesLabel();
        }
    }

    private void createSimple() {
        String foodName = txtFoodName.getText();
        int calories = Integer.parseInt(txtSimpleFoodCalories.getText());
        int quantity = Integer.parseInt(txtFoodQuantity.getText());
        Food food = new SimpleFood(foodName, getCurrentDate(), calories, quantity);
        person.addFood(food);
        updateListViewFoodAdded(food);
    }


    private void loadAddMealScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(addMealFoodLayout));
        try {
            int quantity = Integer.parseInt(txtFoodQuantity.getText());
            MealFood meal = new MealFood(txtFoodName.getText(), getCurrentDate(), quantity);
            person.addFood(meal);
            Parent root = loader.load();
            Stage addFoodWindow = new Stage();
            AddMealFoodController addFoodController = loader.getController();
            addFoodController.setMainUIController(this);
            addFoodController.setMeal(meal);
            addFoodWindow.setScene(new Scene(root, 600, 400));  //TODO: change this to fit window
            addFoodWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: creates a new window for the purpose of being able to search
    //         for a food using the nutritionix API
    public void loadSearchScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(searchFoodLayout));
        try {
            Parent root = loader.load();
            Stage searchWindow = new Stage();
            SearchController searchController = loader.getController();
            searchController.setMainUIController(this);
            searchWindow.setScene(new Scene(root, 600, 400));
            searchWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Person getPerson() {
        return person;
    }

    //MODIFIES: View
    //EFFECTS: changes information posted on view, with the current persons, and also
    //         adds this as an observer to the person
    public void setPerson(Person p) {
        this.person = p;
        lblName.setText(lblName.getText() + " " + person.getName());
        lblAge.setText(lblAge.getText() + " " + person.getAge());
        lblTargetCalories.setText(lblTargetCalories.getText() + " " + person.getTargetCalories());
        person.setObserver(this);
    }


    //EFFECTS: updates the table with foods from new date given
    public void onDateChanged() {
        updateCurrentTimeSelected();
        updateListViewDateChanged();
    }

    private void updateCurrentTimeSelected() {
        LocalDate value = foodDateForDisplay.getValue();
        int year = value.getYear();
        int month = value.getMonthValue();
        int day = value.getDayOfMonth();
        currentTimeSelected = new TimeStamp(year, month, day);
    }

    private void updateListViewDateChanged() {
        foodEatenTableView.getItems().clear();
        foodEatenOnDate = FXCollections.observableArrayList();
        LocalDate value = foodDateForDisplay.getValue();
        int year = value.getYear();
        int month = value.getMonthValue();
        int day = value.getDayOfMonth();
        TimeStamp timeStamp = new TimeStamp(year, month, day);
        List<Food> foodsEaten = person.getFoodsEaten(timeStamp);
        for (Food food : foodsEaten) {
            foodEatenTableView.getItems().add(food);
        }


    }


    //MODIFIES: View
    //EFFECTS: updates the TableView with the newly added food
    public void updateListViewFoodAdded(Food food) {
        if (food.getDate() != null && food.getDate().equals(currentTimeSelected)) {
            foodEatenTableView.getItems().add(food);
        }
    }

    //EFFECTS: when simple checkbox is selected, de-selects other two
    public void onSimpleSelected() {
        if (radioSimple.isSelected()) {
            radioMeal.setSelected(false);
            radioSearch.setSelected(false);
            enableTextFields();
        }
    }

    //EFFECTS: enables the food name and calories textbox
    public void enableTextFields() {
        txtFoodName.setDisable(false);
        txtSimpleFoodCalories.setDisable(false);
    }

    //EFFECTS: when meal checkbox is selected, de-selects other two
    public void onMealSelected() {
        if (radioMeal.isSelected()) {
            txtFoodName.setDisable(false);
            txtSimpleFoodCalories.setDisable(true);
            radioSimple.setSelected(false);
            radioSearch.setSelected(false);
        }
    }

    //EFFECTS: when search checkbox selected, de-selects other two
    public void onSearchSelected() {
        if (radioSearch.isSelected()) {
            txtFoodName.setDisable(true);
            txtSimpleFoodCalories.setDisable(true);
            radioMeal.setSelected(false);
            radioSimple.setSelected(false);
        }
    }

    public void updateCurrentCaloriesLabel() {
        lblCurrentCalories.setText("Current calories: " + person.getTotalCalories());
    }


    //EFFECTS: creates a message box indicating that numerical input
    //         was expected, but was not found
    static void numberFormatAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("You've inputted something wrong!");
        alert.setContentText("Make sure that you're inputting numbers where numbers are required!\"");

        alert.showAndWait();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates an alert box indiciating that user exceeded calories
    @Override
    public void update(Observable o, Object arg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Daily Calorie Information");
        alert.setHeaderText("Calorie Intake Exceeded");
        alert.setContentText("You've exceeded the daily calorie intake!\"");

        alert.showAndWait();
    }
}
