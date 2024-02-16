package bytebuddy.gui;

import java.io.IOException;

import bytebuddy.ByteBuddy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for ByteBuddy using FXML.
 */
public class Main extends Application {

    private ByteBuddy byteBuddy = new ByteBuddy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setByteBuddy(byteBuddy);
            stage.setTitle("ByteBuddy");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
