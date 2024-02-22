package ciara.ui.gui;

import java.io.IOException;

import ciara.exceptions.StorageException;
import ciara.ui.Gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ciara using FXML.
 */
public class Main extends Application {
    // GUI for the application
    private Gui gui = new Gui();

    @Override
    public void start(Stage stage) throws StorageException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);

            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setGui(gui);
            stage.show();
        } catch (IOException e) {
            throw new StorageException("Could not load FXML file");
        }
    }
}
