package henry;

import java.io.IOException;
import java.nio.file.Paths;

import henry.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private final Henry henry = new Henry(Paths.get("data", "tasks.txt").toAbsolutePath().toString());

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHenry(henry);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
