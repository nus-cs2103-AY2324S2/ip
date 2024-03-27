package duke;

import command.Command;
import javafx.util.Pair;

/**
 * Represents the whole program that handles the user interaction, user prompt parsing, task list and file management,
 * and executions on the tasks
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a new Duke Program.
     *
     * @param name       The name given by the user to the bot program
     * @param fileFolder The location where the file of tasks needs to be stored
     * @param fileName   The name of the file storing all tasks
     */
    private Duke(String name, String fileFolder, String fileName) {
        ui = new Ui(name);
        storage = new Storage(fileFolder, fileName);
        taskList = new TaskList(storage.load());
    }

    /**
     * Constructs a new Duke Program.
     *
     * @throws DukeException if initialization is unsuccessful
     */
    public Duke() {
        this("Jerry", "./data", "duke.txt");
    }

    /**
     * Generates a string that answers the prompt given by the user.
     *
     * @return A string that represents the answer
     */
    public Pair<String, Boolean> getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return new Pair<>(command.execute(storage, taskList), command.isExit());
        } catch (DukeException e) {
            return new Pair<>(e.toString(), false);
        }
    }
}
