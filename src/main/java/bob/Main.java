package bob;

import bob.gui.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A GUI for Bob using FXML.
 */
public class Main extends Application {
    private final Bob bob = new Bob(Storage.DATA_PATH);

    @Override
    public void start(Stage stage) {
        MainWindow mw = new MainWindow(stage);
        mw.setBob(bob);
        mw.show();
    }
}
