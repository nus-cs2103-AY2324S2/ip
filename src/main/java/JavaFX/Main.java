package JavaFX;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import LeBron.LeBron;

public class Main extends Application {
    private LeBron leBron = new LeBron();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("LeBron");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLeBron(leBron);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
