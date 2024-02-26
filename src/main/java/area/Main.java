package area;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Area using FXML.
 */
public class Main extends Application {

    private Area area = new Area();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Area");
            fxmlLoader.<MainWindow>getController().setArea(area);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
