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

    @Override
    public String toString() {
        String temp = "Noted. I've removed this task: \n";
        temp += " " + this.task;
        temp += "\nNow you have " + storage.load().size() + " tasks in this list.";
        return temp;
    }
}



