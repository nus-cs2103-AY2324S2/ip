package TaskFlow;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for TaskFlow using FXML.
 */
public class Main extends Application {

    private TaskFlow taskFlow = new TaskFlow();

    /**
     * The start method is called when the application is launched. It initializes the
     * JavaFX stage, loads the main FXML file, sets up the scene, and displays the
     * main window with the associated controller.
     * It also calls the greet method to display a welcome message.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene sc = new Scene(ap);
            Image img = new Image("/images/app_icon.jpg");
            stage.setScene(sc);
            fxmlLoader.<MainWindow>getController().setTaskie(taskFlow);
            stage.setTitle("TaskFlow");
            stage.getIcons().add(img);
            stage.show();
            fxmlLoader.<MainWindow>getController().greet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
