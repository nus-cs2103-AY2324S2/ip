package fluffy;

import fluffy.command.Command;
import fluffy.parser.Parser;
import fluffy.storage.Storage;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main app for Fluffy.
 */
public class FluffyApp extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String filePath = "data/fluffy.txt";

    /**
     * Constructor for FluffyApp.
     */
    public FluffyApp() {
        ui = new Ui(this::handler);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FluffyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * A function for dealing with user input.
     * @param input the input from a user.
     */
    public void handler(String input) {
        assert tasks != null : "tasks should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            storage.save(tasks);
            if (c.isExit()) {
                ui.exit();
            }
        } catch (FluffyException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        ui.start(stage);
    }
}
