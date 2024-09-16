package duke;

/**
 * Represents the result of adding a task to the task list.
 */
public class AddTaskResult {
    private boolean isSuccessful;
    private Task clashingTask;

    /**
     * Constructs a new AddTaskResult object.
     *
     * @param isSuccessful Whether the task was successfully added.
     * @param clashingTask The task that the new task clashes with, if any.
     */
    private AddTaskResult(boolean isSuccessful, Task clashingTask) {
        this.isSuccessful = isSuccessful;
        this.clashingTask = clashingTask;
    }

    /**
     * Returns a new AddTaskResult object representing a successful addition of a task.
     */
    public static AddTaskResult buildSuccessfulResult() {
        return new AddTaskResult(true, null);
    }

    /**
     * Returns a new AddTaskResult object representing a failed addition of a task.
     *
     * @param clashingTask The task that the new task clashes with.
     */
    public static AddTaskResult buildClashingResult(Task clashingTask) {
        return new AddTaskResult(false, clashingTask);
    }

    /**
     * Returns whether the task was successfully added.
     */
    public boolean isSuccessful() {
        return isSuccessful;
    }

    /**
     * Returns the task that the new task clashes with, if any.
     */
    public Task getClashingTask() {
        assert !isSuccessful : "There is no clashing task if the addition was successful";
        return clashingTask;
    }
}
