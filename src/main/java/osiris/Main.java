package osiris;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the Osiris chat application.
 * It initializes an instance of the Osiris chatBot and starts the chat session.
 */
public class Main extends Application {

    /** The Osiris chatBot. */
    private final Osiris chatBot = new Osiris();

    /**
     * The start method overridden from the Application class.
     * It sets up the JavaFX stage, loads the main window FXML, and initializes the Osiris chatBot.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().initialiseOsiris(chatBot);

            Scene scene = new Scene(ap);
            stage.setTitle("Osiris - Your ChatBot");
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
