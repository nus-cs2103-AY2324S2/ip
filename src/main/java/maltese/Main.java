package maltese;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for maltese using FXML.
 */
public class Main extends Application {

    private static final String FILE_PATH = "./data/tasks.txt";
    private static final String MAINWINDOW_FXML_PATH = "/view/MainWindow.fxml";
    private Maltese maltese = new Maltese(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAINWINDOW_FXML_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setmaltese(maltese);
            stage.setTitle("MALTESE");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
