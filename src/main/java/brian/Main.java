package brian;

import java.io.IOException;

import brian.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Brian brian;

    public Main() throws IOException {
        this.brian = new Brian();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            primaryStage.setTitle("Brian");
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setController(brian);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
