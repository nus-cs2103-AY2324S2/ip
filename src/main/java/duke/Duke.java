package duke;

import tasks.TaskList;

/**
 * Class that runs the chatbot.
 *
 * @author Lim Zi Jia
 */
public class Duke {
    /** The storage object that is used in Duke. */
    private Storage storage;

    /** A list that contains all your tasks. */
    private TaskList tasks = new TaskList();

    /** The class that prints to screen and chat. */
    private Ui ui;

    /**
     * Creates a Duke object that is capable of running a chatbot.
     *
     * @param filePath The path of the saved data file.
     * @param fileName The name of the saved data file.
     */
    public Duke(String filePath, String fileName) {
        ui = new Ui();
        storage = new Storage(filePath, fileName);
    }

    /**
     * Runs the main flow of the program. Greet -> Load saved file -> Chat -> Exit/Bye.
     */
    public void run() {
        ui.greet();
        tasks = storage.load();
        ui.chat(tasks, storage);
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("/data/", "duke.txt").run();
    }
}
