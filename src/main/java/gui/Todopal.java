package gui;

import command.Command;
import exceptions.DukeException;
import javafx.application.Platform;
import task.TaskList;
import ui.Ui;
import utilities.Parser;
import utilities.Storage;

/**
 * Duke class responsible for running the program.
 */
public class Todopal {
    /** File name to where the data will be stored. */
    private static final String FILE_NAME = "todopal.txt";
    /** Directory path to where the data will be stored. */
    private static final String DIRECTORY_NAME = "./src/main/data";
    /**
     * Ui responsible for user interaction.
     */
    private Ui ui;
    /**
     * The user's task list.
     */
    private TaskList taskList;
    /**
     * The storage used to access the save file if it exists.
     */
    private Storage storage;

    /**
     * Duke class constructor.
     */
    public Todopal() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_NAME, DIRECTORY_NAME);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Generates a response from the chatbot.
     * @param userInput The input the user keys in.
     * @return The message the chatbot replies.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            if (command.isExit()) {
                Platform.exit();
            }
            return command.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Displays the welcome message upon load up.
     * @return The intended welcome message for the user.
     */
    public String getWelcome() {
        ui.showWelcome();
        return ui.getOutput();
    }
}
