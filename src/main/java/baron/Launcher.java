package baron;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import baron.views.MainWindow;

/**
 * Main entry point for the application. Launches a GUI which the user can chat with Baron.
 */
public class Launcher extends Application {
    private final Baron baron = new Baron();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Baron.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = loader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        loader.<MainWindow>getController().setBotInstance(baron);
        stage.setTitle("Baron.");
        stage.show();
    }
}
