package ellie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Ellie ellie = new Ellie();
    private Image appImage = new Image(this.getClass().getResourceAsStream("/images/appImage.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.getIcons().add(appImage);
            stage.setTitle("Personal Chat bot: Ellie");

            fxmlLoader.<MainWindow>getController().setEllie(ellie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
