import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mike.Mike;

/**
 * A GUI for Mike using FXML.
 */
public class Main extends Application {

    private static final Mike MIKE = new Mike();
    private static final String STAGE_TITLE = "Mike";

    private Stage window;

    @Override
    public void start(Stage stage) {
        try {
            window = stage;
            window.setTitle(STAGE_TITLE);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "fxmlLoader is null";
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "ap is null";
            Scene scene = new Scene(ap);
            window.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMike(MIKE);
            fxmlLoader.<MainWindow>getController().start();
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        closeProgram();
    }

    private void closeProgram() {
        MIKE.save();
        System.out.println("Session terminated: data saved successfully.");
        window.close();
    }
}
