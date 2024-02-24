package luke.exception;

import luke.List;

/**
 * Thrown when the task specified cannot be found in the taskList.
 */
public class TaskNotFoundException extends Throwable {
    private List taskList;

    /**
     * Constructs an instance of TaskNotFoundException
     * @param taskList The task list in which the task is not found.
     */
    public TaskNotFoundException(List taskList) {
        assert taskList != null;
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        if (taskList.getListSize() == 0) {
            return "\nTask not found: list is empty. Please add a task first.";
        }
        return "\nTask not found: Task number should be between 1 and " + taskList.getListSize() + ".";
    }
}
