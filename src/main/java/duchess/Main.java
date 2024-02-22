package duchess;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duchess duchess;

    public Main() {
        try {
            duchess = new Duchess();
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setDuchess(duchess);

            // Add event handler to prevent closing unless user input is "bye"
            stage.setOnCloseRequest(event -> {
                if (!mainWindow.canClose()) {
                    event.consume();

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("To close the application, please say 'bye' to Duchess. It's only polite!");
                    alert.showAndWait();
                }
            });

            stage.setTitle("Duchess");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
