package jimmy.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jimmy.Jimmy;

/**
 * jimmy.gui.Main class to run the bot.
 */
public class Main extends Application {

    static final String FOLDER_PATH = "./data";
    static final String FILE_PATH = FOLDER_PATH + "/jimmy";
    private final Jimmy jimmy = new Jimmy(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("My friend Jimmy");
            fxmlLoader.<MainWindow>getController().setJimmy(jimmy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
