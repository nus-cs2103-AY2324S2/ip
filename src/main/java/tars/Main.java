package tars;

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

    private Tars tars = new Tars();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(tars);
            stage.show();
            stage.setTitle("tars Chatbot");
            fxmlLoader.<MainWindow>getController().Welcome(Ui.greet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
