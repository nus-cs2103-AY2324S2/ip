package App;

import UiRelated.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * serves as program entrypoint
 */
public class Main extends Application {

    private MainWindow mainWindowController;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            mainWindowController = fxmlLoader.getController();
            stage.setOnCloseRequest(event -> {
                mainWindowController.saveTaskList();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
