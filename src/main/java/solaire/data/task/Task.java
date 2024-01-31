package solaire.data.task;

/**
 * Represents a Task that can be marked complete/incomplete.
 */
public abstract class Task {
    private static int count = 0;
    private int id;
    private String taskName;
    private Boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.id = ++count;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Marks the current task as "done".
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the current task as "not done".
     */
    public void unmarkDone() {
        isDone = false;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String checkMark = "[" + (isDone ? "X" : " ") + "] ";
        return checkMark + taskName;
    }
}
