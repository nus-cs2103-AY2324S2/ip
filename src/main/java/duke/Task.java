package duke;

import java.time.format.DateTimeFormatter;

/**
 * Task Class is the parent class for all Task subclasses such as ToDo, Deadline and Event.
 * Task Class contains information such as the Tasktype, TaskDescription and TaskStatus/
 */
public class Task {
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String task;
    private boolean isDone = false;
    private String taskType;

    /**
     * Constructor for Task Class
     * @param taskDesc
     */
    public Task(String taskDesc) {
        this.task = taskDesc;
    }

    /**
     * Constructor for Task Class
     * @param taskDesc
     * @param taskType
     */
    public Task(String taskDesc, String taskType) {
        this.task = taskDesc;
        this.taskType = taskType;
    }

    /**
     * Marks task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Masks task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the completion status of the task for printing to terminal.
     * @return String representation of Task Status.
     */
    public String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[]";
        }
    }

    /**
     * Gets the completion status of the task in binary values when saving the file into txt.
     * @return String representaton of Task Status in binary for file saving purporses.
     */
    public String getStatusBinary() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Gets the task description.
     * @return String of task description.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Gets the task type.
     * @return String representation of the task type.
     */
    public String getTaskType() {
        return "[" + this.taskType + "]";
    }

    /**
     * Method that informs the user task has been created.
     * @return String informing user that task has been created.
     */
    public String getAnnouncement() {
        return "New task created!";
    }

    /**
     * Method generates the string of the task in format for file txt saving.
     * @return String representation of the task in the format for file txt saving.
     */
    public String saveString() {
        return "Type|Done|Description";
    }

    /**
     * Gets the task type for file txt saving purposes.
     * @return String representation of task type for file txt saving purposes.
     */
    public String getTaskTypeSingle() {
        return this.taskType;
    }
}
