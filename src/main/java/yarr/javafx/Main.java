package yarr.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import yarr.Yarr;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    /**
     * The Duke object to be used in the application.
     */
    private Yarr yarr = new Yarr();

    /**
     * Starts the application.
     *
     * @param stage a Stage object representing the primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        try {
            BorderPane borderPane = new BorderPane();
            borderPane.setStyle("-fx-background-color: #1b273b;");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYarr(yarr);
            stage.setTitle("Yarr: The Pirate Task Manager");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/YarrIcon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
