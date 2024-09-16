package duke.tasks;

import duke.TaskList;
import duke.exceptions.InvalidMarkException;

/**
 * The DoAfterTask class represents a task with a description and a task that should be completed before.
 * Inherits from the Task class.
 */
public class DoAfterTask extends Task {

    /**
     * Enum representing the status of the task that needs to be completed before the DoAfterTask.
     * This enum is used in the DoAfterTask class.
     */
    public enum BeforeTaskStatus {
        VALID,
        INVALID,
        NOT_UPDATED
    }
    private int beforeTaskIndexFromData;
    private Task beforeTask;
    private BeforeTaskStatus beforeTaskStatus;

    /**
     * Constructs a DoAfterTask object with the specified description and the task that it must complete before.
     *
     * @param desc the description of the do after task
     * @param beforeTask the task that has to be completed before this task can be completed
     */
    public DoAfterTask(String desc, Task beforeTask) {
        super(desc);;
        this.beforeTask = beforeTask;
        if (this.beforeTask == null) {
            this.beforeTaskStatus = BeforeTaskStatus.INVALID;
        } else {
            this.beforeTaskStatus = BeforeTaskStatus.VALID;
        }
    }

    /**
     * Constructs a DoAfterTask object with the specified description, completetion status,
     * and the task that it must complete before.
     *
     * @param desc the description of the DoAfter task
     * @param isDone the completion status of the DoAfter task ("1" for done, "0" for not done)
     * @param beforeTaskIndex the index of the task that has to be completed before this task can be completed
     */
    public DoAfterTask(String desc, String isDone, int beforeTaskIndex) {
        super(desc, isDone);
        this.beforeTaskIndexFromData = beforeTaskIndex;
        this.beforeTask = null;
        this.beforeTaskStatus = BeforeTaskStatus.NOT_UPDATED;
    }

    /**
     * Updates the task to retrieve relevant beforeTask.
     */
    public void update(TaskList tasks) {
        switch (beforeTaskStatus) {
        case VALID:
            if (!tasks.containsTask(this.beforeTask)) {
                this.beforeTaskStatus = BeforeTaskStatus.INVALID;
                this.beforeTask = null;
            }
            break;
        case NOT_UPDATED:
            if (this.beforeTaskIndexFromData <= 0 || this.beforeTaskIndexFromData >= tasks.size()) {
                this.beforeTaskStatus = BeforeTaskStatus.INVALID;
                this.beforeTask = null;
                break;
            }
            this.beforeTask = tasks.get(this.beforeTaskIndexFromData);
            this.beforeTaskStatus = BeforeTaskStatus.VALID;
            break;
        default:
            break;
        }
    }

    /**
     * Gets the status icon of the deadline task.
     *
     * @return the status icon of the deadline task ("[A][X]" if done, "[A][ ]" if not done)
     */
    public String getStatusIcon() {
        return (this.isDone() ? "[A][X] " : "[A][ ] ");
    }

    /**
     * Gets the status of the beforeTask.
     */
    public void markAsDone() throws InvalidMarkException {
        if (this.beforeTaskStatus == BeforeTaskStatus.INVALID) {
            super.markAsDone();
        } else if (this.beforeTask.isDone()) {
            super.markAsDone();
        } else {
            throw new InvalidMarkException("OOPS!!! DoAfter Task: " + this.getDesc()
                    + " cannot be completed before: " + this.beforeTask.getDesc());
        }
    }

    /**
     * Returns a string representation of the DoAfter task.
     *
     * @return a string representation of the DoAfter task, including its status icon, description,
     *     and the task that has to be completed before
     */
    public String toString() {
        assert this.beforeTaskStatus != BeforeTaskStatus.NOT_UPDATED : "beforeTaskStatus cannot be NOT_UPDATED";
        if (this.beforeTaskStatus == BeforeTaskStatus.INVALID) {
            return this.getStatusIcon() + this.getDesc() + " (after: [task does not exist])";
        }
        return this.getStatusIcon() + this.getDesc() + " (after: " + this.beforeTask.getDesc() + ")";
    }

    /**
     * Returns a string in a standardised format to represent the DoAfter task for saving to file.
     *
     * @param tasks the TaskList this task is saved in
     * @return a string in a standardised format representing the DoAfter task
     */
    public String save(TaskList tasks) {
        String isDone = this.isDone() ? "1" : "0";
        assert this.beforeTaskStatus != BeforeTaskStatus.NOT_UPDATED : "beforeTaskStatus cannot be NOT_UPDATED";
        if (this.beforeTaskStatus == BeforeTaskStatus.INVALID) {
            return "A," + isDone + "," + this.getDesc() + ",-1";
        }
        return "A," + isDone + "," + this.getDesc() + "," + this.beforeTask.getIndex(tasks);
    }
}
