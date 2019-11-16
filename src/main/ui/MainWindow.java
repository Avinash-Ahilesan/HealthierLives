package ui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application {



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Healthier Living");
        Button btn = new Button();
        btn.setText("Create Profile");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Test!");
            }
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(btn);
        primaryStage.setScene(new Scene(layout, 1000, 800));
        primaryStage.show();
    }
}
