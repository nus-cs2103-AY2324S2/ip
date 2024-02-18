package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML and JavaFX to display the chatbot.
 * Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
 */
public class Main extends Application {

    private Ui ui = new Ui();
    private Duke duke = new Duke("data/DevGPT.txt", ui);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Image icon = new Image(this.getClass().getResourceAsStream("/images/DaDevGPT.png"));
            stage.getIcons().add(icon);
            stage.setTitle("DevGPT");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().initialize(duke, ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
