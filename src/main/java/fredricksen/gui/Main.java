package fredricksen.gui;

import java.io.IOException;

import fredricksen.Fredricksen;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Fredricksen fredricksen = new Fredricksen();

    @FXML
    private Image icon = new Image(this.getClass().getResourceAsStream("/images/Up.jpg"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Fredricksen");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setFredricksen(fredricksen, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
