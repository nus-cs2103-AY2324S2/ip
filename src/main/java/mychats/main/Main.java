package mychats.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for MyChats using FXML.
 */
public class Main extends Application {

    private MyChats myChats = new MyChats();

    /**
     * Starts the application by loading the main window.
     *
     * @param stage Stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("My Chats");
            fxmlLoader.<MainWindow>getController().setMyChats(myChats);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
