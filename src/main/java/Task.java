/**
 * This class represents a task that tracks if it is completed
 *
 * @author Billy Ho Cheng En
 */
abstract class Task {
    String task_symbol;
    private final String description;
    private boolean is_done;
    private static final String DONE_SYMBOL = "[X]";
    private static final String UNDONE_SYMBOL = "[ ]";

    /**
     * Constructor for a task list.
     *
     * @param description textual description of the task
     */
    public Task(String description, String task_symbol) {
        this.description = description;
        this.is_done = false;
        this.task_symbol = task_symbol;
    }

    public Task(String description, String task_symbol, Boolean isDone) {
        this.description = description;
        this.is_done = isDone;
        this.task_symbol = task_symbol;
    }

    /**
     * Marks this task as done.
     *
     * @return true if task was change from not done to done. Else false.
     */
    public boolean markDone() {
        if (this.is_done) {
            return false;
        } else {
            this.is_done = true;
            return true;
        }
    }

    /**
     * Marks this task as not done.
     *
     * @return true if task was change from done to not done. Else false.
     */
    public boolean unmarkDone() {
        if (this.is_done) {
            this.is_done = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {

        return this.task_symbol
                + (this.is_done ? DONE_SYMBOL : UNDONE_SYMBOL)
                + " "
                + this.description;
    }
}
