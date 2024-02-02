package duke;

import duke.tasks.TaskList;

/**
 * Main class that runs the program
 */
public class Duke {

    private final String LOGO = "" 
            + "\t    __    _                 \n"
            + "\t   / /   (_)___  __  _______\n"
            + "\t  / /   / / __ \\/ / / / ___/\n"
            + "\t / /___/ / / / / /_/ (__  ) \n"
            + "\t/_____/_/_/ /_/\\__,_/____/  \n";

    private final String NAME = "Linus";

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
        this.parser = new Parser(taskList, ui);
    }

    /**
     * Starts the program
     */
    public void start() {
        ui.print("Hello from\n" + LOGO);
        ui.greeting(NAME);
        this.loadData();
        parser.run();
        this.saveData();
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

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.start();

    }
}

