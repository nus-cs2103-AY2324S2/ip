package duke;



import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author KohGuanZeh
 */
public class Main extends Application {

    final static String BINGUS_LOGO = " B   i   n   g   u   s ";
    final static  String NAME = "Bingus";

    @Override
    public void start(Stage stage) throws DukeException{
        try {
            Duke duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bingus: The Friendly Cat TaskChecker!");
            MainWindow mainWindow = fxmlLoader.getController();
            assert(mainWindow != null);
            mainWindow.setDuke(duke);

            stage.show();
            mainWindow.openingMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

