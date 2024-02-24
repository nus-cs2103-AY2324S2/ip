import java.io.IOException;

import application.Chitty;
import controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class responsible for launching the application.
 */
public class Main extends Application {
    private final Chitty chitty = new Chitty();

    /**
     * Default constructor for the Main class.
     */
    public Main() {}

    /**
     * The entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/MainWindow.fxml"));

            // Assert that the fxmlLoader has a valid location.
            assert fxmlLoader.getLocation() != null;

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("view/stylesheet.css");
            stage.setTitle("Chitty Task Manager");
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setChitty(chitty);

            stage.show();
        } catch (IOException error) {
            System.out.println("Error loading MainWindow.fxml: " + error.getMessage());
        }
    }
}

