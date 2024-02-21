package plato;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * The Main GUI for Plato using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Image image = new Image("/images/DaDuke.png");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(image);
            stage.setTitle("Plato");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
