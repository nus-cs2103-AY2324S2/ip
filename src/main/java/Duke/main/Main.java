package duke.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is the entry point of the Duke application.
 * It initializes the necessary components and starts the application.
 */
public class Main extends Application {
    private Duke duke = new Duke("./data/duke.txt");
    /**
     * The main method is the entry point of the Duke application.
     * It creates the necessary directory and file for storing task data,
     * initializes the Duke object, and starts the application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // Create directory if it does not exist
            Path path = Paths.get("./data");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            // Create data file if it does not exist
            Path filePath = Paths.get("./data/duke.txt");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            // Initialize Duke object with file path and run the application
            Duke duke = new Duke(filePath.toString());
            duke.run();
        } catch (IOException e) {
            System.out.println("An error occurred while reading file");
        }
    }
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().introMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

