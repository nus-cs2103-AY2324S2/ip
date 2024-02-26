package yarr;

import java.util.ArrayList;

import yarr.command.Command;
import yarr.task.Task;

/**
 * Yarr is a simple chatbot application that allows users to manage tasks.
 * It provides a CLI for users to interact with.
 * Users can add, delete, mark as done, find and list tasks.
 * Tasks are stored in a file on the hard disk and loaded on startup.
 * If there is no file to read from, Yarr creates the necessary directory and file.
 */
public class Yarr {
    /** Filepath for reading and writing data. */
    private static final String FILE_PATH = "./data/yarr.txt";

    /** Ui object for handling printing to screen. */
    private Ui ui;

    /** TaskList object for storing and handling tasks. */
    private TaskList tasks;

    /** Storage object for handling reading and writing to the hard disk. */
    private Storage storage;
    /** Boolean to check if the program should exit. */
    private boolean isExit;

    /**
     * Constructor for Yarr class. Instantiates Ui and Storage.
     * Loads TaskList from filepath or makes relevant directory and file if required.
     */
    public Yarr() {
        String filePath = FILE_PATH;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> temp = new ArrayList<>(storage.loadData());
            tasks = new TaskList(temp);
            assert tasks.getTasks() != storage.loadData()
                    : "TaskList and Storage data should not reference the same object for their respective task lists";
        } catch (IllegalArgumentException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a response to user input.
     * @param input a String representing the user's input
     * @return a String representing the response to the user's input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.handleInput(input);
            isExit = c.isExit();
            return c.execute(this.tasks, this.storage, this.ui);
        } catch (IllegalArgumentException e) {
            return ui.printMessage(e.getMessage());
        }
    }

    /**
     * Returns boolean signalling if the program should exit.
     * @return boolean representing whether the program should exit
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns a welcome message to be displayed to the user.
     * @return a String representing the welcome message
     */
    public String showWelcome() {
        return ui.showWelcome();
    }
}
