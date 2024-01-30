package roebot;

import commands.AbstractCommand;
import commands.UserCommand;
import services.TaskList;
import services.Storage;
import services.UI;
import services.parser.Parser;
import java.io.IOException;

/**
 * Represents a RoeBot.
 */
public class RoeBot {
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    public RoeBot() {
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.ui = new UI();
        this.parser = new Parser();
        try {
            storage.loadTasks(this.taskList);
        } catch (IOException e) {
            System.out.println("FIX THIS ERROR");
        }
    }

    public String getResponse(String input) {
        return "Roebot understands: " + input;
    }

    public UserCommand getResult(String userInput) {
        AbstractCommand c = this.parser.parseCommand(userInput);

        return c.execute(taskList, ui, storage);
    }

}
