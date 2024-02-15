package earl.gui;

import java.io.IOException;

import earl.Earl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class encapsulating the GUI application.
 */
public class Main extends Application {

    private final Earl earl = new Earl("./data/earl.txt");
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(new Image(this.getClass()
                    .getResourceAsStream("/images/CrownIcon.png")));
            scene.getStylesheets().add(getClass().getResource(
                    "/stylesheets/style.css").toExternalForm());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Earl");
            fxmlLoader.<MainWindow>getController().setEarl(earl);
            fxmlLoader.<MainWindow>getController().showGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        assert fxmlLoader != null;
        fxmlLoader.<MainWindow>getController().handleExit();
    }
}
