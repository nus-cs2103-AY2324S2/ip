package tommy.task;

/**
 * Abstract class that is the parent class of all types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor a task with status as default not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor a task with its status dependent on the input.
     * Used when retrieving tasks from the database.
     *
     * @param description Description of the task.
     * @param isDone Status of the task.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return Status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Parses the text file string into components that identifies the type of task,
     * task status, and its description. Creates the identified task with relevant information
     * and returns it.
     *
     * @param string String retrieved from the file.
     * @return Newly created task
     */
    public static Task parseFromFileString(String string) {
        String[] components = string.split("\\]");
        String type = components[0].substring(1);
        String statusIcon = components[1].substring(1);
        boolean isDone = checkStatus(statusIcon);
        String description = components[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            return new Deadline(description, isDone);
        case "E":
            return new Event(description, isDone);
        default:
            return null;
        }
    }

    /**
     * Returns the status of the task based on the input icon.
     *
     * @param statusIcon Status icon of the task, where "X" is marked.
     * @return True if the status icon is marked, that is, "X".
     */
    public static boolean checkStatus(String statusIcon) {
        if (statusIcon.equals("X")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the isDone value of the task.
     *
     * @param isDone Value to be set to isDone attribute of the task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task which includes its
     * status and description.
     *
     * @return String representation of the Task.
     */
    public abstract String toString();
}

