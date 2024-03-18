import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class responsible for launching the JavaFX application.
 */
public class Main extends Application {

    private final Duke duke = new Duke();
    private final Image icon = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/snorlax.png")));

    /**
     * The entry point for launching the JavaFX application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);

            stage.setScene(scene);
            stage.setTitle("Snorlax");
            stage.getIcons().add(icon);
            stage.setResizable(false);

            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
