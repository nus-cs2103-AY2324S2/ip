package raphael.task;
import raphael.format.FileFormattable;
public class Task implements FileFormattable {
    private final String description;
    private boolean isDone;
    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

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
            return -1;
        } else {
            this.isDone = true;
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
            return -1;
        } else {
            this.isDone = false;
            return 0;
        }
    }
    public boolean isContaining(String keyword) {
        return this.description.contains(keyword);
    }
    public String toFileFormat() {
        return String.format("%d |&| %s", this.isDone ? 1 : 0, this.description);
    }
    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', this.description);
    }
}
