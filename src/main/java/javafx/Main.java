package javafx;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import primary.William;

/**
 * A GUI for William using FXML.
 */
public class Main extends Application {

    private William william = new William();

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // This one is the given Path, not working for me
            // FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            // This one is an absolute path, working for me
            FXMLLoader fxmlLoader = new FXMLLoader(
                    new File("/Users/khoonsun/ip/src/main/resources/view/MainWindow.fxml").toURI()
                            .toURL());
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setTitle("William ChatBot");
            fxmlLoader.<MainWindow>getController().setWilliam(william);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
