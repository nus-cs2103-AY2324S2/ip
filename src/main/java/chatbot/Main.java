package chatbot;

import java.io.IOException;

import chatbot.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main extends Application {
    private Plana plana;

    public Main() {
        try {
            this.plana = new Plana();
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid save file:\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Storage failed with error:\n" + e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            GridPane root = fxmlLoader.load();

            RowConstraints rcDialog = new RowConstraints();
            rcDialog.setPercentHeight(95.0);
            rcDialog.setVgrow(Priority.SOMETIMES);
            RowConstraints rcInput = new RowConstraints();
            rcInput.setPercentHeight(5.0);
            rcInput.setVgrow(Priority.SOMETIMES);
            root.getRowConstraints().addAll(rcDialog, rcInput);

            ColumnConstraints ccInput = new ColumnConstraints();
            ccInput.setPercentWidth(80.0);
            ccInput.setHgrow(Priority.SOMETIMES);
            ColumnConstraints ccButton = new ColumnConstraints();
            ccButton.setPercentWidth(20.0);
            ccButton.setHgrow(Priority.SOMETIMES);
            root.getColumnConstraints().addAll(ccInput, ccButton);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Plana");
            fxmlLoader.<MainWindow>getController().setPlana(this.plana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
