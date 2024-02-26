package fireraya.Ui;

import java.io.IOException;

import fireraya.main.Fireraya;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Fireraya fireraya = new Fireraya("current_list.txt");

    /**
     * Initializes the program.
     *
     * @param stage Stage for the JavaFX util to run.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Fireraya Chatbot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFireraya(fireraya);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
