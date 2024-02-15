package raphael.task;
import raphael.format.FileFormattable;

/**
 * The task class.
 */
public class Task implements FileFormattable {
    private final String description;
    private boolean isDone;

    /**
     * The overloaded constructor of the task.
     *
     * @param description the description of the task.
     * @param isDone the status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * The constructor of the task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the current task as done.
     *
     * @return 0 if the current task is not done yet, and hence a successful operation can be carried out; -1 otherwise.
     */
    public int check() {
        if (this.isDone) {
            this.checkIsDone(true);
            return -1;
        } else {
            this.checkIsDone(false);
            this.isDone = true;
            this.checkIsDone(true);
            return 0;
        }
    }

    /**
     * Marks the current task as undone.
     *
     * @return 0 if the current task is done, and hence a successful operation can be carried out; -1 otherwise.
     */
    public int uncheck() {
        if (!this.isDone) {
            this.checkIsDone(false);
            return -1;
        } else {
            this.checkIsDone(true);
            this.isDone = false;
            this.checkIsDone(false);
            return 0;
        }
    }
    public boolean isContaining(String keyword) {
        return this.description.contains(keyword);
    }
    public String toFileFormat() {
        return String.format("%d |&| %s", this.isDone ? 1 : 0, this.description);
    }
    private void checkIsDone(boolean expected) {
        String assertionErrorMessage = String.format(
                "The task should be %s", expected ? "done" : "not done");
        assert this.isDone == expected : assertionErrorMessage;
    }
    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', this.description);
    }
}
