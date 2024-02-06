package capone.ui.gui;

import java.io.IOException;

import capone.Capone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Capone using FXML.
 */
public class Main extends Application {

    private Capone capone = new Capone();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCapone(this.capone);
            stage.show();
            this.capone.runGui();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
