import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Lery using FXML.
 */
public class Main extends Application {

    private Lery lery = new Lery();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(true);
            fxmlLoader.<MainWindow>getController().setLery(lery);
            fxmlLoader.<MainWindow>getController().greet();
            stage.setTitle("Lery chatbot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
