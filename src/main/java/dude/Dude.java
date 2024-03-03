package dude;

import java.io.IOException;

import dude.commands.Command;
import dude.commands.Parser;
import dude.exceptions.DudeException;
import dude.tasks.TaskList;
import dude.utils.Storage;


/**
 * The main class of the Duke application.
 * <p>
 * This class is responsible for the main logic of the application.
 * <p>
 * The main loop of the application is responsible for reading user input,
 * parsing it into a command, executing the command and saving the task list to disk.
 **/
public class Dude {
    private static final String STORAGE_LOADING_ERROR_MESSAGE = "An error occurred while loading the tasks. "
            + "Deleting the storage and starting with an empty task list.";
    private static final String STORAGE_SAVING_ERROR_MESSAGE = "An error occurred while saving the tasks to disk.";
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor for the Dude class.
     * <p>
     * The constructor initializes the storage and UI components of the application.
     * It also loads the task list from the storage.
     *
     * @param filePath The file path to the storage file.
     */
    public Dude(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = loadTaskList();
    }

    /**
     * The main method used to interact with dude. User input is passed to this method to execute the
     * appropriate command and send back a response from dude.
     * <p>
     *
     * @param input The user input to be processed.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input, taskList);
        String response = executeCommand(c);
        try {
            saveTaskListToDisk();
        } catch (IOException e) {
            return STORAGE_SAVING_ERROR_MESSAGE;
        }
        return response;
    }

    private static String executeCommand(Command command) {
        try {
            return command.execute();
        } catch (DudeException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    private void saveTaskListToDisk() throws IOException, SecurityException {
        this.storage.saveTasks(taskList);
    }

    private TaskList loadTaskList() {
        TaskList temp;
        try {
            temp = this.storage.loadTasks();
        } catch (Exception e) { //Thrown when file gets corrupted
            System.out.println(STORAGE_LOADING_ERROR_MESSAGE);
            this.storage.deleteStorage();
            temp = new TaskList();
        }
        return temp;
    }

}
