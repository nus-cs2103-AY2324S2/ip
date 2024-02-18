package cvb.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Represents the main entry point for the GUI using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("ConvoBot");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DaConvo.png")));

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Font.loadFont(getClass().getResourceAsStream("/styles/SourceSans3-Regular.otf"), 16);
            Font.loadFont(getClass().getResourceAsStream("/styles/PeaxHandwritingbold.ttf"), 16);
            scene.getStylesheets().add(getClass().getResource("/styles/dark-theme.css").toExternalForm());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
