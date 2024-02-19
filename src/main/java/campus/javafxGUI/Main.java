package campus.javafxGUI;

import campus.Campus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Campus campus = new Campus();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setResizable(true);
            stage.setScene(scene);
            String css = getClass().getResource("/view/styles.css").toExternalForm();
            scene.getStylesheets().add(css);
            fxmlLoader.<MainWindow>getController().setCampus(campus);
            fxmlLoader.<MainWindow>getController().printGreetMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
