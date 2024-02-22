package homie;

import java.util.Scanner;

/**
 * A chatbot programme named Homie that helps you keep track
 * of to-do tasks, deadlines and events. Date and time can be specified for deadlines and events.
 * Other functions include adding tasks, finding tasks, marking or un-marking tasks as done,
 * deleting tasks and listing tasks.
 */
public class Homie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Homie class
     */
    public Homie() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasksFromFile());
        ui.showLoadingError();
    }
    /**
     * Runs the chatbot application.
     */
    public void run() throws HomieException {
        Scanner scanner = new Scanner(System.in); // Create scanner
        String command = scanner.nextLine();

        Parser parser = new Parser(this.tasks, this.ui, this.storage);
        while (!parser.isExit()) {
            parser.parse(command);
            command = scanner.nextLine();
        }
    }

    public static void main(String[] args) throws HomieException {
        new Homie().run();
    }
    /**
     * Returns a String response based on user input to the GUI.
     */
    public String getResponse(String input) throws HomieException {
        Parser parser = new Parser(this.tasks, this.ui, this.storage);
        String outputResponse = parser.parse(input);
        if (parser.isExit()) {
            return outputResponse;
        }
        return outputResponse;
    }
}
