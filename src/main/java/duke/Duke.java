package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;


    public Duke(String saveFile) {
        this.storage = new Storage(saveFile);
    }

    public String load() {
        try {
            tasks = new TaskList(storage.load());
            return "Successfully loaded save file";
        } catch (DukeException e) {
            tasks = new TaskList();
            return e.toString();
        }
    }

    public String greet() {
        return "Hello! I'm notDuke\nWhat can I do for you?";
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public boolean isBye(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.isExit();
        } catch (DukeException e) {
            return false;
        }
    }
}
