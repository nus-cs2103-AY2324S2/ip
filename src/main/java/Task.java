/**
 * class that represents a task instantiated in the tasklist of aaronbot
 */
public class Task {
    private boolean isDone;
    private String taskString;

    public Task(String taskString) {
        this.isDone = false;
        this.taskString = taskString;
    }

    /**
     * function that marks the task as done
     * @return boolean value representing whether the task was newly marked
     */
    public boolean markDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    /**
     * function that unmarks the task as done
     * @return boolean value representing whether the task was newly unmarked
     */
    public boolean unmarkDone() {
        if (!isDone) {
            return false;
        } else {
            isDone = false;
            return true;
        }
    }

    @Override
    public String toString() {
        char completeTick = isDone ? 'X' : ' ';
        return "[" + completeTick + "] " + taskString;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Task)) {
            return false;
        }

        Task task = (Task) obj;
        return (this.taskString.equals(task.taskString));
    }
}
