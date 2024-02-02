package jayne;

import java.util.Scanner;

import jayne.task.TaskList;
/**
 * Represents the main class for the Jayne application.
 * This class encapsulates the initialization of core components
 * such as Ui, TaskList, and Storage, and contains the main program loop.
 */
public class Jayne {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    /**
     * Constructs a new Jayne object.
     * Initializes the UI, storage, and task list components.
     *
     * @param filepath the path to the file where tasks are saved and loaded from.
     */
    public Jayne(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage);
    }
    /**
     * Starts the application.
     * This method runs the main program loop which repeatedly
     * takes in user input, processes it, and outputs the result
     * until the exit command is issued.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        Parser parser = new Parser(taskList, ui);
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new JayneException("Input cannot be empty.");
                }
                isExit = parser.parse(input);
            } catch (JayneException e) {
                System.out.println("\nHuh?!?!? " + e.getMessage() + "\n");
            }
        }
    }
    public static void main(String[] args) {
        new Jayne("./out/jayne.txt").run();
    }
}
