package lrbg.codriver.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import lrbg.codriver.CoDriver;

public class Gui extends Application {

    private final CoDriver coDriver = new CoDriver("./data/codriver.txt");
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCoDriver(this.coDriver);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
