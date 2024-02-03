package tiny;

import java.io.IOException;

import tiny.exceptions.TinyException;

/**
 * Represents the class of our program.
 */
public class Tiny {
    private static final String FILE_PATH = "../../../data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();

    /**
     * Initializes Tiny.
     */
    public Tiny() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (TinyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Tiny to receive user input and prints the appropriate output.
     *
     * @throws IOException If there are exception reading.
     */
    public void run() throws IOException {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                printContent(parser.parse(input, tasks));
                storage.save(tasks.toSave());
                isExit = parser.isExit();
            } catch (TinyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts the program.
     *
     * @param args Arguments that the user pass in.
     */
    public static void main(String[] args) throws IOException {
        new Tiny().run();
    }

    // Printing Methods
    private static void tabPrint(String input) {
        System.out.println("   " + input);
    }

    private static void printLine() {
        tabPrint("____________________________________________________________\n");
    }

    private static void printContent(String input) {
        printLine();
        tabPrint(input);
        printLine();
    }
}
