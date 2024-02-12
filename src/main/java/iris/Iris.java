package iris;

import Exceptions.InvalidInputException;
import commands.Command;
import exceptions.LoadCacheException;
import storage.Storage;
import TaskList.TaskList;
import parser.Parser;
import java.io.*;

public class Iris {

    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    public Iris() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        try {
            this.storage = new Storage();
            storage.loadCache(this.taskList);
        } catch (IOException | InvalidInputException | LoadCacheException e) {
            throw new RuntimeException(e);
        }
    }

    private String executeCommand(Command command) {
        try {
            command.setData(this.taskList, this.storage);
            String result = command.execute();
            this.storage.saveToCache(this.taskList);
            return result;
        } catch (InvalidInputException | LoadCacheException e) {
            return e.getMessage();
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
