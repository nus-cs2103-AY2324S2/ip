package atsisbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is the entry point of the application.
 * It extends the Application class and sets up the main stage.
 */
public class Main extends Application {
    private AtsisBot atsisBot = new AtsisBot();

    /**
     * This method is called when the application is launched.
     * It initializes the main window by loading the FXML file,
     * setting the scene, and displaying the stage.
     *
     * @param stage the primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAtsisBot(atsisBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
