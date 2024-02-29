package chillchief;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private ChillChief chillChief = new ChillChief();

    @Override
    public void start(Stage stage) {
        stage.setTitle("ChillChief");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChillChief(chillChief);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
