package kitchensink.exception;

import kitchensink.List;

/**
 * Thrown when the task specified cannot be found in the taskList.
 */
public class TaskNotFoundException extends Throwable {
    private List taskList;

    public TaskNotFoundException(List taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        if (taskList.getListSize() == 0) {
            return "\nTask not found: list is empty. Please add a task first.";
        }
        return "\nTask not found: Task number should be between 1 and " + taskList.getListSize()
                + ".";
    }
}
