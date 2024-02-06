package duke;

import duke.tasks.TaskList;

/**
 * Main class that runs the program
 */
public class Duke {

    private final String FILE_PATH = "./data/linus.txt";

    private Ui ui;

    private TaskList taskList;

    private Parser parser;

    private Storage storage;

    /**
     * Constructor for Chatbot
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        this.parser = new Parser(taskList, ui, storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        parser.readUserInput(input);
        return ui.toString();
    }

    /**
     * Loads the TaskList tasks data from file
     */
    public void loadData() {
        storage.loadData(taskList, ui);
    }

    /**
     * Saves TaskList tasks into file
     */
    public void saveData() {
        storage.saveData(taskList, ui);
    }

}

