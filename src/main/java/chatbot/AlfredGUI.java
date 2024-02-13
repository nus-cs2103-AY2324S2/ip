package chatbot;

import chatbot.guiElements.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A GUI for Alfred using FXML.
 */

public class AlfredGUI extends Application {
    private Alfred alfred;

    public AlfredGUI() {
        // greet the user
        try{
            this.alfred = new Alfred("data/alfred.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry Master Bruce. I cannot find the file.");
        }
    }

    public static void main(String[] args) {
        // ...
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AlfredGUI.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setAlfred(alfred);
            controller.showGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
