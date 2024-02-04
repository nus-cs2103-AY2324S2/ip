/*
 * Package: Echo
 * Module/Project Name: Echo
 * File: Echo.java
 *
 * Description:
 * This class serves as the entry point for the Echo application, creating instances of Ui, Storage, and TaskManager,
 * and orchestrating the start and end of user interactions.
 *
 */

package Echo;

import Echo.Storage.Storage;
import Echo.Ui.Ui;

import java.io.File;

public class Echo {
    private TaskManager taskManager;
    private Storage storage;
    private Ui ui;
    private final String FILE_PATH = "." + File.separator + "data" + File.separator + "echo.txt";

    /**
     * Constructor for the Echo class.
     * Initializes instances of Ui, Storage, and TaskManager.
     */
    public Echo() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        this.taskManager = new TaskManager(storage);
    }

    /**
     * Main method for starting the Echo application.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        Echo echo = new Echo();
        Ui.greetUser();
        echo.start();
        Ui.endConversation();
    }

    /**
     * Initiates the conversation by starting the interaction with the user through the Ui class.
     */
    public void start() {
        Ui.startConversation(this.taskManager);
    }

    /**
     * Prints the command received from the user.
     *
     * @param command The command entered by the user.
     */
    public void echoCommand(String command) {
        System.out.println("____________________________________________________________");
        System.out.println(command);
        System.out.println("____________________________________________________________");
    }
}
