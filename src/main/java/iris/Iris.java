package iris;

import commands.Command;
import storage.Storage;
import TaskList.TaskList;
import parser.Parser;
import java.io.*;

public class Iris {

    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    public Iris() {
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.parser = new Parser();
        try {
            storage.loadCache(this.taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String executeCommand(Command command) {
        try {
            command.setData(this.taskList);
            String result = command.execute();
            this.storage.saveToCache(this.taskList);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getResponse(String input) {
        Command command = parser.parseCommand(input);
        return executeCommand(command);
    }

    public String greet() {
        return "Hello! I'm Iris.\nWhat can I do for you?";
    }
}
