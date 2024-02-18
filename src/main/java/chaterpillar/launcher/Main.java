package chaterpillar.launcher;

import java.io.IOException;

import chaterpillar.Chaterpillar;
import chaterpillar.exceptions.ChaterpillarException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Chaterpillar chaterpillar = new Chaterpillar();

    public Main() throws ChaterpillarException {
    }

    @Override
    public void start(Stage stage) throws ChaterpillarException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChaterpillar(chaterpillar);
            stage.show();
        } catch (IOException e) {
            throw new ChaterpillarException("IO error in setting up GUI \n" + e.getMessage());
        }
    }
}
