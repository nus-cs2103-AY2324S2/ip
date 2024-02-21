package duke;

import duke.command.Command;
import javafx.application.Platform;

/**
 * The `Duke` class is the entrance of the program.
 * It provides methods to run the program as a chatbot.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Initializes a Duke program.
     *
     * @param filePath A string representing the file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return response;
    }


}
