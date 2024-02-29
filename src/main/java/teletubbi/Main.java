package teletubbi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * A GUI for Teletubbi using FXML.
 */
public class Main extends Application {

    private Teletubbi teletubbi = new Teletubbi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("teletubbi");
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setTeletubbi(teletubbi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

