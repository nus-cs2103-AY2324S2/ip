package Gluti;

import Gluti.helpers.FileStorage;
import Gluti.helpers.Ui;
import Gluti.utils.GlutiException;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The main program that is ran, and creates the FileStorage object
 */

public class Gluti extends Application {
    private FileStorage fStorage;
    private Ui ui;
    public static void main(String[] args) throws GlutiException, IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws GlutiException {
        fStorage = new FileStorage();
        ui = new Ui(fStorage);
        ui.run(primaryStage);
    }
}
