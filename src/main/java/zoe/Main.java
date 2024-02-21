package zoe;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for zoe.Zoe using FXML.
 */
public class Main extends Application {

    private Zoe zoe = new Zoe();
    private Ui ui = new Ui();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Zoe");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().initialize(zoe, ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
