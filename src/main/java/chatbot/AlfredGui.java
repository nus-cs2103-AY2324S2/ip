package chatbot;

import java.io.FileNotFoundException;
import java.io.IOException;

import chatbot.guielements.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Alfred using FXML.
 */

public class AlfredGui extends Application {
    private Alfred alfred;
    /**
     * Constructs the Alfred GUI.
     */
    public AlfredGui() {
        try {
            this.alfred = new Alfred("data/alfred.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry Master Bruce. I cannot find the file.");
        }
    }
    /**
     * Starts the Alfred GUI.
     * @param stage The stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Alfred");
            FXMLLoader fxmlLoader = new FXMLLoader(AlfredGui.class.getResource("/view/MainWindow.fxml"));
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
