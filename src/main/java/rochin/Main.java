package rochin;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for RochinBot using FXML.
 */
public class Main extends Application {

    private RochinBot rochin;

    /**
     * Constructs a Main object.
     * @throws RochinException if an error occurs during initialization of RochinBot.
     */
    public Main() throws RochinException {
        rochin = new RochinBot("./data/rochin.txt", new Response());
    }

    /**
     * Overrides the start method of the Application class.
     * This method is called when the application is launched.
     * It initializes the UI components and sets up the main window.
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("RochinBot");
            fxmlLoader.<MainWindow>getController().setRochin(rochin);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

