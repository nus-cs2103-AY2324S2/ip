package duke;

import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final String FILE_PATH = "data/duke.txt";

    /**
     * Constructor for Duke
     */
    public Duke() {
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException | FileNotFoundException e) {
            tasks = new TaskList(storage);
        }
    }

    /**
     * Gets the response from Duke
     * @param input the input from the user
     * @return the response from Duke
     */
    public String getResponse(String input) {
        try {
            Command command = new Parser().parse(input);
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getGreeting() {
        return "Hello! I'm Taro\nWhat can I do for you?";
    }

}

