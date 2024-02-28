package ui;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Acts as a GUI for Duke using FXML.
 */
public class Main extends Application {

    private BobBot bobBot = new BobBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("BEEP BOOP BOBBOT IS HERE");
            fxmlLoader.<MainWindow>getController().setBot(bobBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
