package univus;

import java.util.Scanner;

import univus.task.TaskList;

/**
 * The main class of Univus chatting interaction app.
 * User can organize and list out the schedule.
 */
public class Univus {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Scanner scanner;

    /**
     * Constructs a new instance of the Univus application.
     *
     * @param filePath The file path for storing and loading task data.
     */
    public Univus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadFromFile();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initializes images for the user and Univus, and sets up other necessary components.
     * This constructor is used to prepare any preliminary data or resources required by the application.
     */
    public Univus() {
        // ...
    }

    /**
     * Runs the Univus application, initiating the interaction loop with the user.
     *
     * @throws UnivusException If an error occurs during the execution of the application.
     */
    public void run() throws UnivusException {
        System.out.println(ui.greet());
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("bye")) {
                System.out.println(ui.bye());
                scanner.close();
                break;
            } else {
                System.out.println(Parser.parse(taskList, message));
            }
        }
        storage.saveToFile(taskList);
    }


    /**
     * Starts the Univus.
     *
     * @param args Command-line arguments (not used in this case).
     * @throws UnivusException exceptions encountered during the execution of the Univus application.
     */
    public static void main(String[] args) throws UnivusException {
        new Univus("./data/Univus.txt").run();
    }
    /**
     * Retrieves the greeting message from the user interface.
     *
     * @return The greeting message.
     */
    public String greet() {
        return ui.greet();
    }
    /**
     * Retrieves the farewell message from the user interface.
     *
     * @return The farewell message.
     */
    public String bye() {
        return ui.bye();
    }
    /**
     * Saves the current state of the task list to the storage.
     */
    public void save() {
        storage.saveToFile(taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            return Parser.parse(taskList, input);
        } catch (UnivusException e) {
            throw new RuntimeException(e);
        }
    }
}
