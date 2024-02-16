package hanxiao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Hanxiao hanxiao = new Hanxiao("data/tasklist.txt");
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/satre.jpg"));

    @Override
    public void start(Stage stage) {
        stage.getIcons().add(dukeImage);
        stage.setTitle("Hanxiao");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(hanxiao);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
