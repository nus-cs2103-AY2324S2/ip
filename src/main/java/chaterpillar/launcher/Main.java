package chaterpillar.launcher;

import java.io.IOException;

import chaterpillar.Chaterpillar;
import chaterpillar.exceptions.ChaterpillarException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String CHATERPILLAR_IMAGE = "/images/chaterpillar.png";
    private static final String USER_IMAGE = "/images/chaterpillar.png";
    private final Image chaterpillarImage = new Image(this.getClass().getResourceAsStream(CHATERPILLAR_IMAGE));
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
            stage.setTitle("Chaterpillar");
            stage.getIcons().add(chaterpillarImage);
            fxmlLoader.<MainWindow>getController().setChaterpillar(chaterpillar);
            stage.show();
        } catch (IOException e) {
            throw new ChaterpillarException("IO error in setting up GUI \n" + e.getMessage());
        }
    }
}
