package tiny;

import tiny.exceptions.TinyException;

import java.io.IOException;

public class Tiny {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();

    /**
     * Initializes Tiny.
     *
     * @param filePath The file path to the file where the data is saved.
     */
    public Tiny(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
        new Tiny("../../../data/tasks.txt").run();
    }

    // Printing Methods
    public static void tabPrint(String input) {
        System.out.println("   " + input);
    }

    public static void printLine() {
        tabPrint("____________________________________________________________\n");
    }

    public static void printContent(String input) {
        printLine();
        tabPrint(input);
        printLine();
    }
}
