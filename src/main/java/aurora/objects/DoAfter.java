package aurora.objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DoAfter class represents a doAfter task, a task that needs to be completed after a certain time
 * or a certain period.
 */
public class DoAfter extends Task {

    /** The time after which this task needs to be done. */
    private LocalDateTime doAfterTime;

    /** The task after which this task needs to be done. */
    private Task task;

    /**
     * The index of the task that is associated with this doAfter. Default set to -1. Deleted tasks
     * from taskList set to -2.
     */
    private int taskNumber = -1;

    private static final String TASK_TYPE_FOR_FILE = "DA";
    private static final String TASK_TYPE = "[DA]";
    private boolean hasNoAssociatedTask;

    /**
     * Constructs a DoAfter task associated with a datetime.
     *
     * @param description Description of the doAfter.
     * @param doAfterTime Datetime associated with the doAfter object.
     */
    public DoAfter(String description, LocalDateTime doAfterTime) {
        super(description);
        this.doAfterTime = doAfterTime;
    }

    /**
     * Constructs a DoAfter task associated with another task.
     *
     * @param description Description of the doAfter.
     * @param taskNumber Index of the other task in the task list associated with the doAfter object.
     */
    public DoAfter(String description, int taskNumber) {
        super(description);
        this.taskNumber = taskNumber;
        this.hasNoAssociatedTask = false;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    /**
     * Returns a String representation of the doAfterTime LocalDateTime object.
     *
     * @return Returns String representation of the doAfterTime LocalDateTime object.
     */
    private String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return this.doAfterTime.format(formatter);
    }

    /**
     * Returns an integer representing the "type" of a specific DoAfter object.
     * Returns 1 if the DoAfter object is associated with a datetime, 2 if the DoAfter object is associated
     * with another task.
     *
     * @return 1 if the DoAfter object is associated with a datetime, 2 if the DoAfter object is associated
     *         with another task.
     */
    public int typeOfDoAfter() {
        if (this.taskNumber == -1) {
            return 1;
        } else {
            return 2;
        }
    }

    public Task getTask() {
        return this.task;
    }

    /**
     * Sets the task number to -2 when the associated task is deleted. Set the task number to 1 less if
     * it is affected by the deletion.
     *
     * @param option "Delete" for deletion of associated task, "affected" for affected tasks by deletion
     * @throws AuroraException If option != "delete" or "affected".
     */
    public void setTaskNumberAfterDelete(String option) throws AuroraException {
        switch (option) {
        case "delete":
            this.taskNumber = -2;
            hasNoAssociatedTask = true;
            break;
        case "affected":
            this.taskNumber = this.taskNumber - 1;
            break;
        default:
            throw new AuroraException("Invalid option for setting taskNumber after deletion.");
        }
    }

    /**
     * Sets the hasNoAssociatedTask field to true if the DoAfter object is to be associated with another task.
     * Sets the hasNoAssociatedTask field to false if the DoAfter object is to be associated with a datetime.
     *
     * @param hasNoAssociatedTask True if the DoAfter has an associated task, false otherwise.
     */
    public void setHasNoAssociatedTask(boolean hasNoAssociatedTask) {
        this.hasNoAssociatedTask = hasNoAssociatedTask;
    }

    @Override
    public String toFileString() {
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        String afterString = getAfterFileString();

        return TASK_TYPE_FOR_FILE + " | " + isDone + " | " + description +
                " | " + this.typeOfDoAfter() + " | " + afterString;
    }

    /**
     * Returns the String representation of either the datetime or the other task associated with the
     * DoAfter object to be saved in the storage file.
     *
     * @return String representation of either the datetime or the other task associated with the
     *         DoAfter object.
     */
    private String getAfterFileString() {
        if (this.typeOfDoAfter() == 1) {
            return dateToString();
        }
        if (hasNoAssociatedTask) {
            return Integer.toString(this.taskNumber);
        }
        return Integer.toString(this.taskNumber) + " | " + this.task.toFileString();
    }

    @Override
    public String toString() {
        String afterString = getAfterString();
        return TASK_TYPE + super.toString() + " (after: " + afterString + ")";
    }

    /**
     * Returns the String representation of either the datetime or the other task associated with the
     * DoAfter object.
     *
     * @return String representation of either the datetime or the other task associated with the
     *         DoAfter object.
     */
    private String getAfterString() {
        if (this.typeOfDoAfter() == 1) {
            return dateToString();
        }
        if (hasNoAssociatedTask || this.task == null) {
            return "I've deleted the task associated from with this doAfter from the list previously!";
        }
        return this.task.toString();
    }

}
