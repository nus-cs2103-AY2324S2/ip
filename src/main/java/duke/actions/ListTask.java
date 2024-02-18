package duke.actions;

import duke.storage.Storage;
import duke.tasks.Task;

/**
 * Provides functionality to list all tasks currently stored in the Duke application's storage.
 * <p>
 * This class is designed to access the application's storage system and retrieve a formatted list
 * of all tasks. Each task is presented alongside its index in the storage list, offering a clear
 * overview of all tasks to the user. In case the storage list is empty, the class will return a
 * simple message indicating that no tasks are available. This functionality supports the user's
 * ability to view all their tasks at a glance.
 * </p>
 */
public class ListTask {
    private Storage storage;

    public ListTask(Storage storage) {
        this.storage = storage;
    }

    /**
     * Generates a string representation of all tasks in the storage's task list.
     * <p>
     * This method iterates through the ArrayList of tasks in the storage, appending each task's
     * string representation to the result string, prefixed by its list index. If the list is empty,
     * it returns a message indicating that the list is empty.
     * </p>
     *
     * @return A string listing all tasks with their index in the list, or a message if the list is empty.
     */
    public String list() {
        if (storage.load().isEmpty()) {
            return "List is Empty !";
        }
        String result = "";
        int count = 1;
        for (Task s : storage.load()) {
            result += count + ". " + s.toString() + "\n";
            count++;
        }
        return result;
    }
}
