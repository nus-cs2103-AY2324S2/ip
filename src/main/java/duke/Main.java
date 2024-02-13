package duke;

import java.io.IOException;

import duke.util.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private String logo = "  ____   __  _  _  __    ___  ___  ____  ___  ____\n"
            + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n"
            + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n"
            + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";


    private Duke duke = new Duke("./data/Duke.txt", "JavAssist", logo);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("JavAssist");
            stage.show();
            fxmlLoader.<MainWindow>getController().welcome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
