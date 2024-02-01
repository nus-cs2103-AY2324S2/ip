package ezra;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main class for the Ezra chatbot.
 */
public class Ezra {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an Ezra object with the specified file path to load saved tasks from.
     *
     * @param filepath The file path for storing tasks.
     */
    public Ezra(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Ezra application, allowing the user to enter commands.
     */
    public void run() {
        ui.greet();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Parser.read(input, storage, tasks);
            if (input.equals("bye")) {
                break;
            }
        }
        scanner.close();
    }

    /**
     * The main method to start the Ezra application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Ezra("data/ezra.txt").run();
    }
}
