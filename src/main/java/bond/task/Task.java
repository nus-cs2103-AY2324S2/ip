package bond.task;

/**
 * Represents a task in the Bond task management program.
 *
 * @author Benny Loh
 * @version 0.2
 */
public abstract class Task {

    protected String name;
    private Boolean isCompleted;

    /**
     * Constructor for the Task class.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
    }

    public void markAsComplete() {
        this.isCompleted = true;
    }

    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(" [%s] %s", this.isCompleted ? "X" : " ", this.name.trim());
    }
}
