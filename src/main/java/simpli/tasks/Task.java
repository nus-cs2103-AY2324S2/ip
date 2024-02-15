package simpli.tasks;

public class Task {
    private final String name;
    private boolean isDone;

    /**
     * Initializes a task with the specified attributes.
     *
     * @param isDone boolean representing if a task is completed or not.
     * @param name String.
     */
    public Task(boolean isDone, String name) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks task as completed.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks task as uncompleted.
     */
    public void undone() {
        this.isDone = false;
    }

    /**
     * Returns true if task is completed.
     *
     * @return boolean value representing if a task is completed or not.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Converts the task into a comma-separated values (csv) String representation.
     * @return String representing the csv value for the task.
     */
    public String toCsv() {
        return String.format("%s,%s", isDone ? 1 : 0, name);
    }

    /**
     * Returns the task String representation.
     * @return String representation of the task.
     */
    public String toString() {
        return String.format(
                "[%c] %s",
                this.isDone ? 'X' : ' ',
                this.name
        );
    }
}
