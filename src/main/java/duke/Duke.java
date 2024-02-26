package duke;


import duke.commands.Command;
import duke.commands.ExitProgram;
import duke.exceptions.DukeException;
import duke.exceptions.StorageException;
import duke.fileUtils.FilePaths;
import duke.gui.Launcher;
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
 * 'to-do', 'deadline', 'event', 'list', 'done', 'delete', and 'bye' to interact with Duke.
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

    /**
     * Constructs a Duke object.
     * Initializes the UI, task list, storage, and scanner objects.
     */
    public Duke() throws StorageException {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(taskList);
        storage.load();

    }

    public static void main(String[] args) {
        Launcher.main(args);
    }


    public String getResponse(String input) throws StorageException {
        String response;
        Command c;
        try {
            ui.storeCommand(input);
            c = Parser.parseUserInput(input);
            response = c.execute(taskList, ui, storage);
            storage.save();
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
