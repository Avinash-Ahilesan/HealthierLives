package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import model.Person;


public class Controller {

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

    }
}
