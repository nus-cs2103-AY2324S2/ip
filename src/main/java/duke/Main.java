package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("EchoPilot");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        MainWindow controller = loader.getController();
        Duke duke = new Duke("./data/duke.txt");
        controller.setDuke(duke);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
