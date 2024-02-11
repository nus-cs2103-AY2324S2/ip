package earl.gui;

import java.io.IOException;

import earl.Earl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class encapsulating the GUI application.
 */
public class Main extends Application {

    private final Earl earl = new Earl("./data/earl.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource(
                    "/stylesheets/fontstyle.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Earl");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setEarl(earl);
            fxmlLoader.<MainWindow>getController().showGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        earl.getResponse("bye");
    }
}
