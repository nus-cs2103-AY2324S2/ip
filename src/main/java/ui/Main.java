package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import primary.William;

/**
 * A GUI for William using FXML.
 */
public class Main extends Application {

    private William william = new William();

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setTitle("William");
            fxmlLoader.<MainWindow>getController().setWilliam(william);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
