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
import model.food.SimpleFood;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

public class MainUIController {
    private ObservableList<Food> foodEatenOnDate;
    private Person person;
    public Button btnAddFood;
    public Label lblName;
    public Label lblAge;
    public Label lblTargetCalories;
    public Label lblWeight;

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

    public void initialize() {
        radioSimple.setSelected(true);

        TableColumn<String, Food> foodNameColumn = new TableColumn<>("Food Name");
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Food> caloriesColumn = new TableColumn<>("Calories");
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));

        foodEatenTableView.getColumns().add(foodNameColumn);
        foodEatenTableView.getColumns().add(caloriesColumn);
        foodEatenTableView.setPlaceholder(new Label("No foods have been added yet"));
    }

    public void onAddFoodClicked() {
        Food food = null;
        if (radioSimple.isSelected()) {
            String foodName = txtFoodName.getText();
            int calories = Integer.parseInt(txtSimpleFoodCalories.getText());
            int quantity = Integer.parseInt(txtFoodQuantity.getText());
            food = new SimpleFood(foodName, getCurrentDate(), calories, quantity);
            person.addFood(food);
            updateListViewFoodAdded(food);
        }
        if (radioSearch.isSelected()) {
            loadSearchScene();

        }
        if (radioMeal.isSelected()) {
            //TODO: code
        }


    }

    public void loadSearchScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchfood.fxml"));
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

    public TimeStamp getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month += 1;
        if (month == 13) {
            month = 1;
        }
        TimeStamp ts = new TimeStamp(year, month, day);
        return ts;
    }


    public void setPerson(Person p) {
        this.person = p;
        lblName.setText(lblName.getText() + " " + person.getName());
        lblAge.setText(lblAge.getText() + " " + person.getAge());
        lblTargetCalories.setText(lblTargetCalories.getText() + " " + person.getTargetCalories());
    }

    public void onDateChanged() {
        updateCurrentTimeSelected();
        updateListViewDateChanged();
    }

    public void updateCurrentTimeSelected() {
        LocalDate value = foodDateForDisplay.getValue();
        int year = value.getYear();
        int month = value.getMonthValue();
        int day = value.getDayOfMonth();
        currentTimeSelected = new TimeStamp(year, month, day);
    }

    public void updateListViewDateChanged() {
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

    public void updateListViewFoodAdded(Food food) {
        if (food.getDate() != null && food.getDate().equals(currentTimeSelected)) {
            foodEatenTableView.getItems().add(food);
        }
    }

    public void onSimpleSelected() {
        if (radioSimple.isSelected()) {
            radioMeal.setSelected(false);
            radioSearch.setSelected(false);
            enableTextFields();
        }
    }

    public void enableTextFields() {
        txtFoodName.setDisable(false);
        txtSimpleFoodCalories.setDisable(false);
    }

    public void onMealSelected() {
        if (radioMeal.isSelected()) {
            txtFoodName.setDisable(false);
            txtSimpleFoodCalories.setDisable(true);
            radioSimple.setSelected(false);
            radioSearch.setSelected(false);
        }
    }

    public void onSearchSelected() {
        if (radioSearch.isSelected()) {
            txtFoodName.setDisable(true);
            txtSimpleFoodCalories.setDisable(true);
            radioMeal.setSelected(false);
            radioSimple.setSelected(false);
        }
    }
}
