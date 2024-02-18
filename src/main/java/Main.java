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

    private static final String applicationTitle = "GeePeeTee";
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
            AnchorPane.setLeftAnchor(ap, 0.0);
            AnchorPane.setRightAnchor(ap, 0.0);
            AnchorPane.setTopAnchor(ap, 0.0);
            AnchorPane.setBottomAnchor(ap, 0.0);

            assert ap != null : "FXMLLoader failed to load AnchorPane";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(applicationTitle);
            assert stage.getScene() != null : "Scene was not set correctly";

            MainWindow controller = fxmlLoader.<MainWindow>getController();
            assert controller != null : "FXMLLoader failed to load MainWindow controller";
            assert geepeetee != null : "GeePeeTee was not initialised correctly";
            controller.setGeePeeTee(geepeetee);
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
