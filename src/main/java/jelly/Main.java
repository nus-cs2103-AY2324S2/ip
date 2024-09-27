package jelly;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Jelly jelly = new Jelly();

    @Override
    public void start(Stage stage) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Jelly");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/Title_icon.png")));
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setJelly(jelly);
            stage.show();
            stage.setOnCloseRequest(we -> jelly.saveStorage());

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

