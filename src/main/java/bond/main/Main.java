package bond.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bond using FXML.
 */
public class Main extends Application {

    private Bond bond = new Bond();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bond");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setBond(bond);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

