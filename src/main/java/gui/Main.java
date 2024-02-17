package gui;

import java.io.IOException;

import ezra.Ezra;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class for the GUI application of Ezra using FXML.
 */
public class Main extends Application {

    private Ezra ezra = new Ezra("data/ezra.txt");

    /**
     * The main entry point for the application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEzra(ezra);
            stage.setResizable(false);
            stage.setTitle("Ezra");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
