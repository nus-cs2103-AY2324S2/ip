package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML, setting up the primary stage and scene for the application.
 */
public class Main extends Application {

    private Duke duke;

    @Override
    public void init() throws Exception {
        super.init();
        // Initialize your Duke object here to handle any initialization exceptions properly.
        this.duke = new Duke("./data/jamie.txt");
    }

    @Override
    public void start(Stage stage) {
        try {
            // Ensure the FXML file path is correct.
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            if (Main.class.getResource("/view/MainWindow.fxml") == null) {
                throw new IOException("Cannot find FXML file.");
            }

            // Load the root layout from the FXML.
            AnchorPane rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);

            // Set the Duke instance in the controller.
            MainWindow controller = fxmlLoader.getController();
            if (controller == null) {
                throw new IOException("Controller cannot be null.");
            }
            controller.setDuke(duke);

            stage.setTitle("Duke");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1); // Exit the application due to FXML loading failure.
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
