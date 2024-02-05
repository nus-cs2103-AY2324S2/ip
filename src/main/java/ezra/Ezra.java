package ezra;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class for the Ezra chatbot.
 */
public class Ezra {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an Ezra object with the specified file path to load saved tasks from.
     *
     * @param filepath The file path for storing tasks.
     */
    public Ezra(String filepath) {
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
        Ui.greet();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Parser.generateReply(input, storage, tasks);
            if (input.equals("bye")) {
                break;
            }
        }
        scanner.close();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return Parser.generateReply(input, storage, tasks);
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
