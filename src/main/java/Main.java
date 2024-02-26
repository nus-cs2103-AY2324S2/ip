import java.io.IOException;

import cal.Cal;
import controller.MainWindow;
import exceptions.CalException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 */
public class Main extends Application {
    private Cal cal = new Cal();

    public Main() throws CalException {

    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Cal");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCal(cal);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
