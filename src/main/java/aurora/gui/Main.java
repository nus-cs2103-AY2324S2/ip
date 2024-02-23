package aurora.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import aurora.Aurora;

/**
 * The Main class is the graphics interface for the Aurora application.
 * Code reused and adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Main extends Application {

    /** Aurora API from within the application. */
    private Aurora aurora = new Aurora();

    /** JavaFX scene used to host all graphical outputs. */
    private Scene scene;

    /** Image imported for icon. */
    private Image auroraImage = new Image(this.getClass().getResourceAsStream("/images/Aurora.jpg"));

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Aurora");
            stage.getIcons().add(auroraImage);
            stage.setResizable(false);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Window.fxml"));
            AnchorPane ap = fxmlLoader.load();
            scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Window>getController().setAurora(aurora);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
