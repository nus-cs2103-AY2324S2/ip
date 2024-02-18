package someboty.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import someboty.SomeBoty;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String FILE_PATH = System.getProperty("user.dir");
    private SomeBoty bot = new SomeBoty(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            //String cssFilePath = "/view/Application.css";
            //scene.getStylesheets().add(Main.class.getResource(cssFilePath).toExternalForm());

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(bot);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
