package fishstock.ui;

import java.io.IOException;

import fishstock.FishStock;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for FishStock using FXML.
 */
public class Main extends Application {
    private FishStock fishstock = new FishStock();
    private Image fishStockImage = new Image(this.getClass().getResourceAsStream("/images/FishStock.png"));

    /**
     * Starts the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("FishStock");
            stage.getIcons().add(fishStockImage);
            fxmlLoader.<MainWindow>getController().setFishStock(fishstock);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the GUI.
     */
    @Override
    public void stop() {
        fishstock.exit();
    }
}
