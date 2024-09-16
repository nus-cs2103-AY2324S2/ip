package duke.actions;

import duke.storage.Storage;
import duke.tasks.Task;


/**
 * Facilitates adding tasks to the Duke application's storage.
 * <p>
 * This class represents an action to add a new task into the storage system of the Duke application.
 * It holds a reference to the storage where the task needs to be added and the task itself.
 * After adding a task, the class can also provide a string representation of the addition to be used
 * for output in the Parser class, indicating the successful addition of the task and the total number
 * of tasks currently stored.
 * </p>
 */
public class AddTask {
    private Storage storage;
    private Task t;

    /**
     * Constructs an {@code AddTask} action with specified storage and task.
     * <p>
     * This constructor initializes the {@code AddTask} object with the storage system where the task will be added
     * and the task to be added. The {@code storage} is used to access and modify the list of tasks, while the {@code t}
     * represents the specific task to add.
     * </p>
     * @param storage The storage system of the Duke application where the task will be added. It must be initialized
     *                and capable of storing tasks.
     * @param t       The task to be added to the storage system. This task should not be {@code null}.
     */
    public AddTask(Storage storage, Task t) {
        this.storage = storage;
        this.t = t;
    }

    /**
     * Adds a task to the storage's list of tasks.
     * This method accesses the storage to retrieve the current list of tasks and appends the task represented by
     * the {@code t} instance variable to this list.
     */
    public void add() {
        this.storage.load().add(t);
    }

    /**
     * Provides a string representation of the task addition action.
     * <p>
     * This method generates a message indicating that a task has been successfully added to the storage. It also
     * displays the added task and the total number of tasks in the storage. This string representation is useful for
     * providing feedback to the user about the result of their action.
     * </p>
     * @return A string detailing the task that was added, along with the current total number of tasks in the storage.
     */
    public String toString() {
        String temp = "Got it. I've added this task: \n";
        temp += " " + this.storage.load().get(this.storage.load().size() - 1).toString();
        temp += "\nNow you have " + this.storage.load().size() + " tasks in the list.";
        return temp;
    }
}
