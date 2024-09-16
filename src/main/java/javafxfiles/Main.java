package javafxfiles;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Steven using FXML.
 */
public class Main extends Application {

    private Steven steven = new Steven();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Steven");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSteven(steven);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
