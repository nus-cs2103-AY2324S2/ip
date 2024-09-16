package duke.actions;

import duke.storage.Storage;

/**
 * Manages the unmarking of tasks within the Duke application, setting them as not completed.
 * <p>
 * This class provides the functionality to change the status of a specific task from "completed" to "not completed"
 * based on its index in the task list. It is instantiated with a reference to the storage where tasks are maintained
 * and the index of the task to be updated. This action is part of the application's task management capabilities,
 * allowing users to dynamically adjust task statuses as needed.
 * </p>
 */
public class UnmarkTask {
    private Storage storage;
    private int index;
    /**
     * Constructs a new UnmarkTask object to manage the unmarking of tasks within the Duke application.
     * <p>
     * This constructor initializes an instance of the UnmarkTask class with references to the storage where tasks
     * are maintained and the index of the task to be updated. It is used to instantiate objects that provide the
     * functionality to change the status of a specific task from "completed" to "not completed" based on its index in
     * the task list. This action is part of the application's task management capabilities, allowing users to
     * dynamically adjust task statuses as needed.
     * </p>
     * @param storage The storage instance where tasks are maintained.
     * @param index   The index of the task to be updated within the task list.
     */
    public UnmarkTask(Storage storage, int index) {
        this.storage = storage;
        this.index = index;
    }

    /**
     * Marks the specified task as not done based on its index attribute.
     * <p>
     * This method accesses the task identified by the given index in the storage's task list and updates its status to
     * "not done." It constructs and returns a confirmation message that includes the task's details, indicating to the
     * user that the task status has been successfully updated.
     * </p>
     *
     * @return A string message confirming the task has been marked as not done, along with the task's details.
     */
    public String unmark() {
        storage.load().get(index - 1).unmark();
        String temp = "OK, I've marked this task as not done yet: \n";
        temp += storage.load().get(index - 1).toString();
        return temp;
    }
}
