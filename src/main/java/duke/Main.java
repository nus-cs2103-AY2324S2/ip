package duke;

import java.awt.*;
import java.awt.Taskbar.Feature;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main GUI for Duke using FXML.
 */
public class Main extends Application {


    @Override
    public void start(Stage stage) {
        try {

            Image image = new Image("/images/DaDuke.png");
            //Inspired from https://datmt.com/backend/how-to-set-icon-on-dock-taskbar-for-javafx-application/
            //THisis for apple
            if (Taskbar.isTaskbarSupported()) {
                var taskbar = Taskbar.getTaskbar();
                if (taskbar.isSupported(Feature.ICON_IMAGE)) {
                    final Toolkit currentToolkit = Toolkit.getDefaultToolkit();
                    java.awt.Image dockIcon = currentToolkit.getImage(getClass().getResource("/images/DaDuke.png"));
                    taskbar.setIconImage(dockIcon);
                }
            }

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(image);
            stage.setTitle("DaDUKE PRO MAX");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}