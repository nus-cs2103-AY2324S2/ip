package seedu.chatteroo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import seedu.chatteroo.Chatteroo;
import seedu.chatteroo.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Chatteroo chatteroo;


    @Override
    public void start(Stage stage) {
        try {
            chatteroo = new Chatteroo();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatteroo(chatteroo);
            stage.setTitle("Chatteroo");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
