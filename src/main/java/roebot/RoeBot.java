package roebot;

import java.io.IOException;

import commands.AbstractCommand;
import commands.UserCommand;
import services.Storage;
import services.TaskList;
import services.UI;
import services.parser.Parser;


/**
 * Represents a RoeBot.
 */
public class RoeBot {
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    /**
     * RoeBot Constructor.
     */
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

    /**
     * Parses the user input to create an UserCommand.
     *
     * @param userInput The user's input as a string.
     * @return UserCommand A UserCommand if input is valid.
     */
    public UserCommand getResult(String userInput) {
        AbstractCommand c = this.parser.parseCommand(userInput);
        return c.execute(taskList, ui, storage);
    }
}
