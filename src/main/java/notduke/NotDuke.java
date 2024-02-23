package notduke;

import notduke.command.Command;
import notduke.notdukeexception.NotDukeException;
import notduke.parser.Parser;
import notduke.storage.Storage;
import notduke.tasklist.TaskList;

public class NotDuke {
    private Storage storage;
    private TaskList tasks;


    public NotDuke(String saveFile) {
        this.storage = new Storage(saveFile);
    }

    public String load() {
        try {
            tasks = new TaskList(storage.load());
            return "Successfully loaded save file";
        } catch (NotDukeException e) {
            tasks = new TaskList();
            return e.toString();
        }
    }

    public String greet() {
        return "Hello! I'm notDuke\nWhat can I do for you?";
    }

    public String reminder() {
        return tasks.remind();
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, storage);
        } catch (NotDukeException e) {
            return e.toString();
        }
    }

    public boolean isBye(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.isExit();
        } catch (NotDukeException e) {
            return false;
        }
    }
}
