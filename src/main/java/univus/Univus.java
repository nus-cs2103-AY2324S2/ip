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
     * Runs the Univus application, initiating the interaction loop with the user.
     *
     * @throws UnivusException If an error occurs during the execution of the application.
     */
    public void run() throws UnivusException {
        ui.greet();
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("bye")) {
                ui.bye();
                scanner.close();
                break;
            } else {
                Parser.parse(taskList, message);
            }
        }
        storage.saveToFile(taskList);
    }

    /**
     * The main method to start the Univus application.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws UnivusException If an error occurs during the execution of the application.
     */
    public static void main(String[] args) throws UnivusException {
        new Univus("./data/Univus.txt").run();
    }
}
