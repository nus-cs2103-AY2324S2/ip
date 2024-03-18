package coat.ui;

import java.io.IOException;

import coat.Coat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Coat using FXML.
 */
public class Main extends Application {

    private static Stage stage;

    private final Coat coat = new Coat();



    @Override
    public void start(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Coat the Chatbot");
            stage.setResizable(false);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/icon.jpg")));
            fxmlLoader.<MainWindow>getController().setCoat(coat);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }
}
