package tasks;

/**
 * The Task class is the superclass of all specific Task subclasses
 */
public abstract class Task {
    protected String description;

    protected String taskCode;
    protected boolean isDone;

    /**
     * Constructor for an empty Task
     */
    public Task() {
        this.description = "NO DESCRIPTION";
        this.isDone = false;
    }

    /**
     * Constructor for a Task
     *
     * @param description Description of task
     * @param taskCode String containing a single letter representing the task type
     */
    public Task(String description, String taskCode) {
        this.description = description;
        this.taskCode = taskCode;
        this.isDone = false;
    }

    /**
     * Constructor for a task
     *
     * @param description Description of task
     * @param taskCode String containing a single letter representing the task type
     * @param isDone Completion status of the task
     */
    public Task(String description, String taskCode, boolean isDone) {
        this.description = description;
        this.taskCode = taskCode;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskCode() {
        return this.taskCode;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String getTaskDetails();

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Converts a task from a string to Task object
     * This abstract method must be implemented by all child classes
     *
     * @param saveDetails Task in string format
     * @return Task as a Task object
     */
    public abstract Task convertSaveToTask(String saveDetails);

    /**
     * Converts a task from Task object to string (for saving)
     *
     * @return Task as a string
     */
    public abstract String convertTaskToSave();
}
