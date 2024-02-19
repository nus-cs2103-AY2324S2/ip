package Gluti.Gui;

import Gluti.Gluti;
import Gluti.helpers.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final Gluti gluti = new Gluti();
    private Ui ui = new Ui();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Glutii");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().initialize(gluti, ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}