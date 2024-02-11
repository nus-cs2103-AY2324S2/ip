package duke;

import exceptions.DukeException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import storage.Storage;
import storage.TaskList;
import ui.UserInterface;

/**
 * Main driver class, initializes the required classes and starts the operation.
 */
public class Stille extends Application {
    private final UserInterface ui;
    private final Storage storage;
    private final TaskList list;

    public Stille() {
        this.storage = new Storage();
        this.list = new TaskList();
        this.ui = new UserInterface(list);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(this.ui.getScene());
        primaryStage.setTitle("Stille");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
        primaryStage.show();


        try {
            list.loadFromSaveFormat(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            Platform.exit();
        }

        ui.showOpeningMessage();
    }

    /**
     * At the end of operation, save the current tasklist, output closing message.
     */
    @Override
    public void stop() {
        try {
            this.storage.save(this.list.toSaveFormat());
        } catch (DukeException e) {
            ui.showError(e);
        } finally {
            ui.showClosingMessage();
        }
    }

}
