package cat;

import java.io.IOException;

import cat.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This is the main entrypoint of the program.
 * This sets up the main GUI window and hooks up a Duke instance to it.
 */
public class Main extends Application {
    private final Cat cat = new Cat();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("The Cat that Lives in your Walls");
            fxmlLoader.<MainWindow>getController().setDuke(cat);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        cat.save();
    }
}
