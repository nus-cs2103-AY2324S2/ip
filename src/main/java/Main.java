import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Lai lai = new Lai();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert Main.class.getResource("/view/MainWindow.fxml") != null : "FXML for MainWindow not found";

            AnchorPane ap = fxmlLoader.load();
            assert ap instanceof AnchorPane : "FXMLLoader failed to load the AnchorPane";

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            assert stage.getScene() != null : "Stage failed to set the scene";

            fxmlLoader.<MainWindow>getController().setLai(lai);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
