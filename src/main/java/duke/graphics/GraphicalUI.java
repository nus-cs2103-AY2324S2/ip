package duke.graphics;

import java.io.IOException;
import java.util.Objects;

import duke.logic.Logic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for HAL9000 using FXML.
 */
public class GraphicalUI extends Application {

    private final Logic logic = new Logic();
    private final Image icon = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/hal9000.png")));
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUI.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUserInterface(logic);
            stage.show();
            stage.setTitle("HAL9000");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().greet();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
