package kervyn;

import kervyn.Storage;
import kervyn.Tasks.TaskList;
import kervyn.Ui;

import java.io.IOException;

/**
 * Main class for the Kervyn application.
 * This class initializes the application and starts the interaction with the user.
 */
public class Kervyn {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new instance of the Kervyn application with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     * @throws RuntimeException If an I/O error occurs when reading tasks from storage.
     */
    public Kervyn(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readTasks());
        } catch (IOException e) {
            taskList = new TaskList();
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts the Kervyn application. Initializes the chatbot interface and begins interaction.
     *
     * @throws IOException If an I/O error occurs during the interaction.
     */
    public void run() throws IOException {
        ui.startChatBot(this.taskList, this.storage);
    }

    /**
     * The entry point for the Kervyn application.
     *
     * @param args Command-line arguments, not used in this application.
     * @throws IOException If an I/O error occurs when starting the application.
     */
    public static void main(String[] args) throws IOException {
        new Kervyn("data/tasks.txt").run();
    }
}
