package duke;

import duke.command.Command;
import java.io.FileNotFoundException;

/**
 * Duke, a task manager bot. Also named "MR. WONG"
 * @author wongkj12
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final String FILEPATH = "data/tasks.txt";

    private boolean newTaskListCreatedInStorage = false;

    /**
     * Creates an instance of the bot.
     */
    public Duke() {
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage, storage.load());
        } catch (DukeException | FileNotFoundException e) {
            newTaskListCreatedInStorage = true;
            tasks = new TaskList(storage);
        }
    }

    /**
     * Given user input, tries to parse it as a command and executes the command.
     * @param input
     * @return Response from Duke
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the welcome message when user opens Duke.
     * @return The welcome message.
     */
    public String getWelcome() {
        return "Hello! I'm MR WONG. How may I help you today?" + (newTaskListCreatedInStorage ?
                "\nYou did not have a task list saved in storage, so I've created one for you :)" : "");
    }

}