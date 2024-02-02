package duke;//package duke;

import duke.task.*;
import duke.storage.*;
import duke.ui.*;
import duke.parser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class serves as the main class for the chatbot.
 * It manages the initialization of components and runs the main loop for user interaction.
 */
public class Duke {

    /** The storage handler for managing tasks. */
    private Storage storage;

    /** The task list containing tasks managed by Duke. */
    private TaskList taskList;

    /** The user interface for displaying messages to the user. */
    private Ui ui;

    /** The parser for interpreting user commands and executing corresponding actions. */
    private Parser parser;

    /**
     * Constructs a Duke object with the specified file path and bot name.
     *
     * @param filePath the file path where tasks are stored
     * @param botName  the name of the Duke bot
     */
    public Duke(String filePath, String botName) {
        this.storage = new Storage(filePath);
        this.ui = new Ui(botName);
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        this.taskList = new TaskList(loadedTasks, this.ui);
        this.parser = new Parser(this.ui, this.storage, this.taskList);
    }

    /**
     * Runs the program.
     *
     * @throws IOException if an I/O error occurs during application execution
     */
    public void run() throws IOException {
        ui.sendWelcome();
        boolean isBye = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            parser.execute(userInput);
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

    /**
     * The main method for starting the Duke chatbot application.
     *
     * @param args the command-line arguments (not used)
     * @throws IOException if an I/O error occurs during application execution
     */
    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt", "Hammy").run();
    }
}
