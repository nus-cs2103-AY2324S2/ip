package drew.ui;

import drew.Drew;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Drew using FXML.
 */
public class Main extends Application {

    private Drew drew = new Drew("save/tasks.txt");

    @Override
    public void start(Stage stage) {
        AnchorPane ap = new MainWindow(drew);
        Scene scene = new Scene(ap);
        stage.setTitle("Drew");
        stage.setScene(scene);
        stage.show();
    }
}
