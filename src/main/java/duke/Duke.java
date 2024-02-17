package duke;


import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.StorageException;
import duke.fileUtils.FilePaths;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;
import duke.mainUtils.Parser;

import java.util.Scanner;

/**
 * Duke is a simple task management application that allows users to manage their tasks via a command-line interface.
 * Users can add, delete, mark as done, and list tasks. Duke also provides basic error handling and data persistence.
 * <p>
 * Duke initializes with a {@link Storage} object to handle data storage and retrieval,
 * a {@link TaskList} to manage tasks, a {@link Ui} object to handle user interactions,
 * and a {@link Scanner} object to read user input from the console.
 * </p>
 * <p>
 * To use Duke, simply run the {@link #main(String[])} method. Duke will load existing tasks from the specified
 * tasks save file, display a welcome message, and wait for user commands. Users can enter commands such as
 * 'todo', 'deadline', 'event', 'list', 'done', 'delete', and 'bye' to interact with Duke.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see Command
 * @see DukeException
 * @see StorageException
 * @see FilePaths
 * @see Storage
 * @see TaskList
 * @see Ui
 * @see Parser
 * @see Scanner
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Scanner scanner;

    /**
     * Constructs a Duke object.
     * Initializes the UI, task list, storage, and scanner objects.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(FilePaths.TASKS_SAVE_FILE_PATH, taskList);
        scanner = new Scanner(System.in);

    }

    /**
     * Runs the Duke application.
     * Displays a welcome message, loads tasks from the storage file, and waits for user commands.
     *
     * @throws StorageException if there is an error loading or saving tasks from/to the storage file.
     */
    public void run() throws StorageException {
        ui.displayStart();
        boolean isExit = false;
        storage.load();
        while (!isExit) {
            try {
                ui.storeCommand(scanner.nextLine());
                String[] splitFullCommand = ui.getCommand();
                ui.displayLine();
                if (splitFullCommand[0].equalsIgnoreCase("bye")) {
                    isExit = true;
                }
                Command c = Parser.parseUserInput(splitFullCommand);
                c.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.displayErrorGraphic(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
        storage.save();
    }

    /**
     * Main method to run the Duke application.
     * Instantiates a Duke object and invokes its {@link #run()} method.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
