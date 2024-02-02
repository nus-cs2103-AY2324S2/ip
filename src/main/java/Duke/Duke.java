package Duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class representing the Duke chatbot application. Duke manages the user interface, task list, and storage.
 * Duke processes user input, executes commands, and interacts with the user.
 */
public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.ensureDataFileExists();
            tasks = storage.loadTasksFromFile();
        } catch (DukeDataCorruptedException e) {
            ui.showDukeDataCorruptionMessage(e);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new one.");
            // Handle the case when the data file is not found, and create a new one
            ui.showFileNotFoundExceptionMessage();
        } catch (IOException e) {
            // Handle IOException (e.g., file-related issues)
            ui.showIoExceptionMessage();
        } catch (NumberFormatException e) {
            // Handle NumberFormatException (e.g., when parsing integers)
            ui.showNumberFormatExceptionMessage();
        }
    }

    /**
     * Runs the Duke application.
     * Displays welcome messages, processes user input, and executes commands until the exit command is given.
     */
    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            try {
                String userInput = ui.getUserInput();
                if (Parser.isExitCommand(userInput)) {
                    ui.showGoodbyeMessage();
                    storage.saveTasksToFile(tasks);
                    break;
                }
                if (userInput.trim().isEmpty()) {
                    throw new DukeException("Please enter an action and a task");
                }
                //System.out.println((tasks.getTask(0)).isDone());
                Command command = Parser.parseCommand(userInput);
                command.execute(tasks, ui, storage);
                storage.saveTasksToFile(tasks);
            } catch (DukeException e) {
                ui.showDukeExceptionMessage(e);
                tasks = new TaskList();
            } catch (NumberFormatException e) {
                // Handle NumberFormatException (e.g., when parsing integers)
                ui.showNumberFormatExceptionMessage();
            } catch (IOException e) {
                ui.showIoExceptionMessage();
            }
        }
    }

    /**
     * The entry point of the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}

/**
 * Exception class representing a corrupted data file in Duke.
 */
class DukeDataCorruptedException extends Exception {

    /**
     * Constructs a DukeDataCorruptedException with the specified error message.
     *
     * @param message The error message describing the data corruption issue.
     */
    public DukeDataCorruptedException(String message) {
        super(message);
    }
}

/**
 * Exception class representing a generic exception in Duke.
 */
class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}


