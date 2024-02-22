package alpa.main;

import alpa.commands.Command;
import alpa.commands.Parser;
import alpa.exceptions.AlpaException;
import alpa.tasks.TaskList;
import alpa.utils.Storage;

/**
 * Represents the main class of the Alpa application.
 * Alpa is a task management application that helps users keep track of their tasks.
 */
public class Alpa {
    private static final String FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Represents the main class of the Alpa application.
     */
    public Alpa() {
        storage = new Storage(FILE_PATH);
        tasks = new TaskList();
        try {
            tasks.addAll(storage.loadTasks());
        } catch (AlpaException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }
    }

    /**
     * Returns the task list.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return tasks;
    }

    /**
     * Returns the storage object used by the application.
     *
     * @return the storage object
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Processes input from the GUI and returns a response.
     *
     * @param input User input from the GUI.
     * @return Response to be displayed in the GUI.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.executeCommand(tasks, storage);
        } catch (AlpaException e) {
            return e.getMessage();
        }
    }
}
