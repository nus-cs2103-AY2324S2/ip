package remi.utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import remi.gui.MainWindow;
import remi.model.Ui;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class GuiLauncher extends Application {

    private Ui ui = new Ui();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiLauncher.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUi(ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

