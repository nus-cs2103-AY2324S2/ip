package nicky;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Nicky using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Nicky nicky = new Nicky();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            Parent root = loader.load();
            MainWindow mainWindow = loader.getController();
            mainWindow.setNicky(nicky);
            primaryStage.setTitle("Nicky v0.2");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
