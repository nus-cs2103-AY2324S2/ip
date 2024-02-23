package lilybot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author SE-EDU
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications

/**
 * A GUI for LilyBot using FXML.
 */

public class Main extends Application {

    private LilyBot lilyBot = new LilyBot(Storage.getFilePathToLilyBotTxt());

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("LilyBot");
            fxmlLoader.<MainWindow>getController().setLilyBot(lilyBot);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author