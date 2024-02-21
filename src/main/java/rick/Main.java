package rick;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Rick using FXML.
 */
public class Main extends Application {
    private Rick rick = new Rick();

    /**
     * Starts the programme.
     * @param stage the primary stage for this application, onto which
     *      the application scene can be set.
     *      Applications may create other stages, if needed, but they will not be
     *      primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            loader.<MainController>getController().setRick(rick);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
