package duke.actions;

import duke.storage.Storage;



/**
 * Handles the deletion of tasks from the Duke application's storage based on their index.
 * <p>
 * This class encapsulates the functionality required to delete a task from the storage's list of tasks.
 * It is constructed with a reference to the storage and the index of the task to be deleted. The deletion
 * process involves removing the task from the storage's task list and storing the deleted task's string
 * representation for feedback purposes. The {@code toString} method provides a message that includes the
 * details of the deleted task and the updated total number of tasks in storage.
 * </p>
 */
public class DeleteTask {
    private Storage storage;
    private int index;
    private String task;

    /**
     * Creates a {@code DeleteTask} action with specified storage and the index of the task to be deleted.
     * <p>
     * This constructor initializes a {@code DeleteTask} object with a reference to the storage system from where
     * the task will be deleted and the index of the task in the list. It is important to note that the index
     * should be based on user-visible numbering (typically starting from 1) which will be adjusted internally
     * to match the zero-based indexing of Java collections. The task's string representation is initially empty
     * and will be populated upon deletion.
     * </p>
     * @param storage The storage system of the Duke application from which the task will be deleted.
     * @param index   The index of the task to be deleted, as seen by the user, starting from 1.
     */
    public DeleteTask(Storage storage, int index) {
        this.storage = storage;
        this.index = index;
        this.task = "";
    }

    /**
     * Deletes a task from the storage's list of tasks based on its index.
     * <p>
     * This method removes the task at the specified index from the storage's ArrayList of tasks.
     * The index is adjusted to account for list indexing starting at 0. Before removal, the task's
     * string representation is stored in {@code this.task} to allow for reporting what was deleted.
     * </p>
     */
    public void delete() {
        this.task = storage.load().get(index - 1).toString();
        storage.load().remove(index - 1);
    }

    /**
     * Returns a string representation of the task deletion action.
     * <p>
     * After a task is deleted, this method generates a confirmation message including the details of the deleted task
     * and the updated total number of tasks remaining in storage. This feedback is essential for informing the user
     * about the outcome of their delete command, ensuring clarity in the application's state changes.
     * </p>
     * @return A string message detailing the task that was removed and the current total number of tasks in storage.
     */
    @Override
    public String toString() {
        String temp = "Noted. I've removed this task: \n";
        temp += " " + this.task;
        temp += "\nNow you have " + storage.load().size() + " tasks in this list.";
        return temp;
    }
}



