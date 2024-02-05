package baron;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    Baron baron = new Baron();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Baron.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = loader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        loader.<MainWindow>getController().setBotInstance(baron);
        stage.show();
    }
}
