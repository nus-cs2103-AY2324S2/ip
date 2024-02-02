package duke;

import duke.command.Command;
import duke.storage.Storage;

import java.util.Objects;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private TaskList myList;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
            myList = new TaskList(storage.load());
        } catch (Storage.InvalidStorageFilePathException | Storage.StorageOperationException e) {
            ui.showError(e.getMessage());
        }
    }

    /** Runs the program until termination.  */
    public void run() {
        boolean isContinue = true;
        ui.showWelcome();

        while (isContinue) {
            String input = ui.getCommand();
            try {
                if (Objects.equals(input, "bye")) {
                    storage.save(myList);
                    isContinue = false;
                }
            } catch (Storage.StorageOperationException e) {
                ui.showError(e.getMessage());
            }
            try {
                Command command = Parser.parseCommand(input);
                command.execute(myList, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}