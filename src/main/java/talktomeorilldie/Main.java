package talktomeorilldie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for TalkToMeOrIllDie using FXML.
 */
public class Main extends Application {

    private final TalkToMeOrIllDie talktomeorilldie = new TalkToMeOrIllDie();

    public Main() throws IOException {
    }

    /**
     * Starts the TalkToMeOrIllDie program.
     * @param stage The stage to start the program.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTalktomeorilldie(talktomeorilldie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
