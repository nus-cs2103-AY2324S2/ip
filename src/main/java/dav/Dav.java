package dav;

import java.util.Scanner;

/**
 * Dav application class for managing tasks.
 */
public class Dav {

    private static final String FILE_PATH = "./data/dav.txt";
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;

    /**
     * Constructor for Dav class. Initializes UI, storage, and task list.
     */
    public Dav() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method to run the Dav application.
     */
    public void run() {
        ui.greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = ui.getUserInput(scanner);
            Parser.parseUserInput(userInput, tasks, ui, storage);
        } while (!userInput.equalsIgnoreCase("bye"));

        ui.exit();
    }

    /**
     * Main method to start the Dav application.
     * @param args Command line arguments (not used).
     */public static void main(String[] args) {
        new Dav().run();
    }
}
