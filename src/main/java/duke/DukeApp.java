package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main app for Fluffy.
 */
public class DukeApp extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String filePath = "data/duke.txt";

    /**
     * Constructor for DukeApp.
     */
    public DukeApp() {
        ui = new Ui(this::handler);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * A function for dealing with user input.
     * @param input the input from a user.
     */
    public void handler(String input) {

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            storage.save(tasks);
            if (c.isExit()) {
                ui.exit();
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        ui.start(stage);
    }
}
