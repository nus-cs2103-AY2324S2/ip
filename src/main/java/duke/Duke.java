package duke;

import java.util.Scanner;

/**
 * Main class of chatbot.
 */
public class Duke {
    private final Storage storage;
    private final TaskList items;
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath path of text file to store data
     * @throws DukeException if file path is invalid
     */
    public Duke(String filePath) throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.items = new TaskList(storage.readData());
    }

    /**
     * Responsibe for running the chatbot.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;

        ui.welcome();

        while (isValid) {
            String command = scanner.nextLine();
            try {
                isValid = Parser.parse(command, items, ui);
                storage.saveData(items);
            } catch (DukeException e) {
                ui.error(e.getMessage());
            }
            ui.emptyLine();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/test.txt").run();
    }
}