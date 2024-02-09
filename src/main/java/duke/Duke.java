package duke;

import duke.command.Command;
import java.util.Scanner;

/**
 * Duke is a simple chatbot application that allows users to manage tasks.
 * It provides a CLI for users to interact with.
 * Users can add, delete, mark as done, find and list tasks.
 * Tasks are stored in a file on the hard disk and loaded on startup.
 * If there is no file to read from, Duke creates the necessary directory and file.
 */
public class Duke {
    /** Filepath for reading and writing data. */
    private static final String FILE_PATH = "./data/duke.txt";

    /** Ui object for handling printing to screen. */
    private Ui ui;

    /** TaskList object for storing and handling tasks. */
    private TaskList tasks;

    /** Storage object for handling reading and writing to the hard disk. */
    private Storage storage;

    /**
     * Constructor for Duke class. Instantiates Ui and Storage.
     * Loads TaskList from filepath or makes relevant directory and file if required.
     *
     * @param filePath Filepath for loading and saving TaskList data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IllegalArgumentException e) {
            System.out.println("Couldn't read from file");
            tasks = new TaskList();
        }
    }

    /**
     * Helper function that manages the running of the chatbot.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.handleInput(scanner.nextLine());
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Duke application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }


}