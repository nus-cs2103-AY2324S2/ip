import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Riz riz = new Riz();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("RizBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRiz(this.riz);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
