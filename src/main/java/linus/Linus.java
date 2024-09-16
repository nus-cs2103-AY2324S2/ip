package linus;

import linus.tasks.TaskList;

/**
 * Main class that runs the program
 */
public class Linus {

    private final String FILE_PATH = "./data/linus.txt";

    private final String NAME = "Linus";

    private Ui ui;

    private TaskList taskList;

    private Parser parser;

    private Storage storage;

    /**
     * Constructor for Chatbot
     */
    public Linus() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(ui, FILE_PATH);
        this.parser = new Parser(taskList, ui, storage);
    }

    /**
     * 
     * @param input String of user input
     * @return String response from the chatbot
     */
    public String getResponse(String input) {
        parser.readUserInput(input);
        return ui.toString();
    }

    /**
     * Greets the user
     * @return String response of greeting
     */
    public String greeting() {
        ui.greeting(NAME);
        return ui.toString();
    }

    /**
     * Loads the TaskList tasks data from file
     */
    public String loadData() {
        storage.loadData(taskList);
        return ui.toString();
    }

    /**
     * Saves TaskList tasks into file
     */
    public void saveData() {
        storage.saveData(taskList);
    }

}

