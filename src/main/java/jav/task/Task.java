package jav.task;

import jav.exception.InvalidParamException;
import jav.manager.StorageManager;

/**
* A task consists of a description and whether it is marked as done or not.
*/
public class Task {
    /** The type of task. */
    protected StorageManager.StorageType type;

    /** Description of task. */
    protected String description;

    /** Whether the task is done. */
    protected boolean isMarked;

    /**
     * Constructs a new Task.
     * 
     * @return a new Task.
     */
    public Task() {
        type = StorageManager.StorageType.TASK;
        description = "task";
        isMarked = false;
    }

    /**
     * Constructs a new Task.
     *
     * @param params a string containing the information about the task.
     * @param isMarked whether the task is marked.
     * @return a new Task.
     * @throws InvalidParamException if the parameters are invalid.
     */
    public Task(String params, boolean isMarked) throws InvalidParamException {
        type = StorageManager.StorageType.TASK;
        description = params;
        this.isMarked = isMarked;
    }

    public String getDescription() {
        return description;
    }

    public StorageManager.StorageType getType() {
        return type;
    }

    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Updates whether the task is marked or not.
     * 
     * @param isMarked whether the task is marked.
     */
    public void updateMark(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s",
                            isMarked ? "X" : " ",
                            description);
    }

    /**
     * Returns a string of the parameters in a file ready format.
     *
     * @return a string of the parameters in a file ready format.
     */
    public String getFileFormatParam() {
        return description;
    }
}
