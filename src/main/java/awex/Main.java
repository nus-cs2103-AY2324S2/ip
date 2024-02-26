package awex;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class Main extends Application {

    private Awex awex = new Awex();

    //@@author {alex-setyawan}-reused
    //adapted from https://github.com/nus-cs2103-AY2324S2/ip/pull/4/files
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(awex);
            fxmlLoader.<MainWindow>getController().showGreeting(awex.sendGreeting());
            stage.setTitle("AWEX - A Wonderful Experience");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //@@author
}