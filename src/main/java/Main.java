import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private GeePeeTee geepeetee = new GeePeeTee("GeePeeTee.txt");

    /**
     * Starts the application.
     * 
     * @param stage The stage to be used to start the application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGeePeeTee(geepeetee);
            String errorMessage = geepeetee.getInitializationErrorMessage();
            if (errorMessage != null) {
                fxmlLoader.<MainWindow>getController().handleInitializationError(errorMessage);
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
