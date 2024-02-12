package aurora.objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DoAfter class represents a doAfter task, a task that needs to be completed after a certain time or a certain
 * period.
 */
public class DoAfter extends Task {

    /**
     * The time after which this task needs to be done.
     */
    private LocalDateTime doAfterTime;

    /**
     * The task after which this task needs to be done.
     */
    private Task task;

    /**
     * The index of the task that is associated with this doAfter. Default set to -1.
     */
    private int taskNumber = -1;

    private static final String TASK_TYPE_FOR_FILE = "DA";
    private static final String TASK_TYPE = "[DA]";

    /**
     * Constructor for a DoAfter task, whereby the task needs to be done after
     * a certain time.
     *
     * @param description Description of the doAfter.
     * @param doAfterTime date and time associated with this doAfter
     */
    public DoAfter(String description, LocalDateTime doAfterTime) {
        super(description);
        this.doAfterTime = doAfterTime;
    }

    /**
     * Constructor for a DoAfter task, whereby the task needs to be done after
     * another task.
     *
     * @param description Description of the doAfter.
     * @param taskNumber taskNumber representing the task to be associated with this doAfter.
     */
    public DoAfter(String description, int taskNumber) {
        super(description);
        this.taskNumber = taskNumber;
    }

    /**
     * Setter for the task associated with the doAfter.
     *
     * @param task Task associated with the doAfter.
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Getter for the taskNumber
     *
     * @return taskNumber associated with the doAfter if it exists, null otherwise
     */
    public int getTaskNumber() {
        return this.taskNumber;
    }

    /**
     * Formats the local datetime to string.
     *
     * @return doAfterTime in String format.
     */
    private String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return this.doAfterTime.format(formatter);
    }

    /**
     * Method to retrieve the type of DoAfter a specific DoAfter instance.
     *
     * @return 1 if the DoAfter is for after a specific time, 2 if the DoAfter is for after a specific task.
     */
    public int typeOfDoAfter() {
        if (this.taskNumber == -1) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Getter for the task
     *
     * @return Task associated with the doAfter.
     */
    public Task getTask() {
        return this.task;
    }

    @Override
    public String toFileString() {
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        String afterString = "";
        if (this.typeOfDoAfter() == 1) {
            afterString = dateToString();
        } else {
            afterString = Integer.toString(this.taskNumber);
        }
        return TASK_TYPE_FOR_FILE + " | " + isDone + " | " + description +
                " | " + this.typeOfDoAfter() + " | " + afterString;
    }

    @Override
    public String toString() {
        String afterString = "";
        if (this.typeOfDoAfter() == 1) {
            afterString = dateToString();
        } else {
            afterString = this.task.toString();
        }
        return TASK_TYPE + super.toString() + " (after: " + afterString + ")";
    }

}
