package guanguan;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GUI for GuanGuan using FXML.
 */
public class Main extends Application {

    private final GuanGuan guanguan;

    public Main() throws GgException {
        this.guanguan = new GuanGuan("data/test.txt", new Gui());
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("GuanGuan");

            fxmlLoader.<MainWindow>getController().setGuanGuan(guanguan);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
