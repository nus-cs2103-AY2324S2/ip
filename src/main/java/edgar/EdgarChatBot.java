package edgar;

import commands.Command;
import util.Parser;
import util.Ui;
import util.TaskList;
import util.Storage;
import commands.UserCommand;
import java.io.IOException;

/**
 * The EdgarChatBot class represents a simple chatbot application that manages tasks.
 * It utilizes TaskList, Storage, Ui, and Parser for various functionalities.
 */
public class EdgarChatBot {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new EdgarChatBot object.
     * Initializes the task list, storage, user interface, and parser.
     */
    public EdgarChatBot() {
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            storage.loadFromFile(this.taskList);
        } catch (IOException e) {
            System.out.println("Oops! There was an error loading the task list from file.");
        }
    }

    /**
     * Processes the user input and returns the corresponding UserCommand.
     *
     * @param userInput The input provided by the user.
     * @return The UserCommand representing the response to the user input.
     */
    public UserCommand commandResult(String userInput) {
        Command command = this.parser.parseCommand(userInput);
        return command.execute(taskList, ui, storage);
    }
}
