package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;
import ui.HealthierLives;

import java.io.IOException;


public class ManagerUIController {

    public Button btnCreateProfile;
    public TextField txtName;
    public TextField txtAge;
    public TextField txtTargetCalories;
    public ListView<Person> personListView;
    public ObservableList<Person> personList;
    HealthierLives tracker = new HealthierLives();


    public void initialize() {
        System.out.println("initialized");
        personList = FXCollections.observableArrayList();
        personListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        personListView.setItems(personList); //line of error
    }

    public void createProfileClicked() {
        Person p = new Person(txtName.getText(), Integer.parseInt(txtAge.getText()),
                Integer.parseInt(txtTargetCalories.getText()));
        if (personList.contains(p)) {
            //TODO: add alert box asking them if they're sure they want to continue
            System.out.println("person already here");
        }
        personList.add(p);
        System.out.println(p);
    }

    public void deletePersonClicked() {
        Person personToDelete = personListView.getSelectionModel().getSelectedItem();
        System.out.println(personToDelete);
        if (personToDelete != null) {
            personList.remove(personListView.getSelectionModel().getSelectedItem());
        }
    }

    public void enterProgramClicked() {
        Person p = personListView.getSelectionModel().getSelectedItem();
        if (p != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainui.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) personListView.getScene().getWindow();
                // these two of them return the same stage
                // Swap screen
                MainUIController mainUIController = loader.getController();
                mainUIController.setPerson(p);
                stage.setScene(new Scene(root, 800, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
