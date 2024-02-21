package meanduke.tasks;

/**
 * This class represents a task that tracks if it is completed.
 */
public abstract class Task implements Savable {
    private static final String DONE_SYMBOL = "[X]";
    private static final String UNDONE_SYMBOL = "[ ]";
    private final String description;
    private boolean isDone;
    private String taskSymbol;

    /**
     * Constructs a new Task with the specified description and task symbol.
     *
     * @param description textual description of the task
     * @param taskSymbol textual symbol that represents the type of Task
     */
    public Task(String description, String taskSymbol) {
        assert !description.isEmpty();
        this.description = description;
        this.isDone = false;
        this.taskSymbol = taskSymbol;
    }

    /**
     * Constructs a new Task with the specified description, task symbol and completion state.
     *
     * @param description textual description of the task
     * @param taskSymbol textual symbol that represents the type of Task
     * @param isDone      boolean value that determines if the initialised Task is completed or not
     */
    public Task(String description, String taskSymbol, Boolean isDone) {
        assert !description.isEmpty();
        this.description = description;
        this.isDone = isDone;
        this.taskSymbol = taskSymbol;
    }

    /**
     * Marks this task as done.
     *
     * @return true if task was change from not done to done. Else false.
     */
    public boolean markDone() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

    /**
     * Marks this task as not done.
     *
     * @return true if task was change from done to not done. Else false.
     */
    public boolean unmarkDone() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String saveString() {
        return this.description + "\n" + this.isDone;
    }

    @Override
    public String toString() {
        return this.taskSymbol
                + (this.isDone ? DONE_SYMBOL : UNDONE_SYMBOL)
                + " "
                + this.description;
    }
}
