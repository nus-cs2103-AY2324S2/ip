package yapper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "./src/main/java/data/yapper.Yapper.txt";
    private Yapper yapper = new Yapper(FILE_PATH, System.in);


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Yapper");
            assert fxmlLoader.getController() != null : "FXMLLoader controller should not be null";
            fxmlLoader.<MainWindow>getController().setYapper(yapper);
            assert stage != null : "Stage should not be null";
            stage.show();
            assert fxmlLoader.getController() != null : "FXMLLoader controller should not be null";
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}