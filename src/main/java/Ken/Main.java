package ken;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ken.control.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Ken ken = new Ken();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ken");
            fxmlLoader.<MainWindow>getController().setKen(ken);
            stage.show();
            fxmlLoader.<MainWindow>getController().sayHi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}

