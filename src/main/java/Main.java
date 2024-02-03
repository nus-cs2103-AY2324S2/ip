import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import naruto.Naruto;

/**
 * The Main class represents the entry point of the Naruto chatbot.
 * It contains the main method that starts the execution of the program.
 */
public class Main extends Application {
    public static void main(String[] args) {
        new Naruto();
        while (Naruto.hasNextAction()) {
            Naruto.act();
            Naruto.listen();
        }
    }

    private Naruto naruto = new Naruto();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNaruto(naruto);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
