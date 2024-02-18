package duke.actions;

import duke.storage.Storage;

/**
 * Enables marking of tasks as completed within the Duke application's storage system.
 * <p>
 * This class is tasked with identifying a specific task by its index and updating its status to reflect completion.
 * Constructed with references to the storage system and the target task's index, it facilitates the marking process,
 * ensuring that the designated task is accurately flagged as done. The operation is confirmed through a message
 * detailing the task's new status, providing immediate feedback to the user on the successful update.
 * </p>
 */
public class MarkTask {
    private Storage storage;
    private int index;

    /**
     * Constructs a {@code MarkTask} action with specified storage and the index of the task to be marked as completed.
     * <p>
     * This constructor initializes a {@code MarkTask} object with a reference to the storage
     * system where the tasks are managed and the index of the task that
     * needs to be marked as done. The index provided should correspond to the position of the task
     * in the user-visible list, which is then adjusted internally to accommodate zero-based indexing used in storage.
     * </p>
     * @param storage The storage system of the Duke application, responsible for managing tasks.
     * @param index   The 1-based index of the task to be marked as completed, as viewed by the user.
     */
    public MarkTask(Storage storage, int index) {
        this.storage = storage;
        this.index = index;
    }

    /**
     * Marks a task as done based on its index in the task list.
     * <p>
     * This method updates the status of a specified task to "completed" and generates a confirmation message.
     * It retrieves the task by its index (adjusted for zero-based indexing) from the storage's list of tasks,
     * marks the task as done, and returns a message indicating the task has been successfully marked.
     * </p>
     * @return A string message confirming the task has been marked as done, along with the task's details.
     */
    public String mark() {
        storage.load().get(index - 1).mark();
        String temp = "Nice! I've marked this task as done: \n";
        temp += storage.load().get(index - 1).toString();
        return temp;
    }
}
