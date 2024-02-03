package duke.actions;

import duke.tasks.Task;
import duke.storage.Storage;


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

    public AddTask(Storage storage, Task t) {
        this.storage = storage;
        this.t = t;
    }

    /**
     * Adds a task to the storage's list of tasks.
     * 
     * This method accesses the storage to retrieve the current list of tasks and appends the task represented by
     * the {@code t} instance variable to this list.
     */
    public void add() {
        this.storage.load().add(t);
    }

    public String toString() {
        String temp = "Got it. I've added this task: \n";
        temp += " " + this.storage.load().get(this.storage.load().size() - 1).toString();
        temp += "\nNow you have " + this.storage.load().size() + " tasks in the list.";
        return temp;
    }
}
