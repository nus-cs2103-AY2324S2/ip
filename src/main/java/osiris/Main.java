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

    private final Osiris chatBot = new Osiris();

    @Override
    public void start(Stage stage) {
        // chatBot.startChat();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setDuke(chatBot);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
