package jmsandiegoo.tyrone.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jmsandiegoo.tyrone.Tyrone;

import java.io.IOException;

/**
 * Set's up the application's root node and initializes the respective scene and stage.
 */
public class Main extends Application {

    private final Tyrone tyrone = new Tyrone();

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            BorderPane bp = fxmlLoader.load();
            Scene scene = new Scene(bp);
            primaryStage.setScene(scene);
            fxmlLoader.<MainWindowController>getController().setTyrone(this.tyrone);
            fxmlLoader.<MainWindowController>getController().start();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
