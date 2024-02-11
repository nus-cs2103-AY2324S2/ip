package duke.tasks;

/**
 * Bare task that has a task name and a boolean condition on whether the task is done.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a task with task name appended, not completed by default.
     *
     * @param n Specified task name.
     */
    public Task(String n) {
        this.name = n;
        this.isDone = false;
    }

    /**
     * Constructs a task specifying a name and if it is complete.
     *
     * @param n Specified task name.
     * @param d Specifying if the task is done.
     */
    public Task(String n, boolean d) {
        name = n;
        isDone = d;
    }

    @Override
    public String toString() {
        String d = (isDone) ? "X" : " ";
        return String.format("[%s] %s", d, name);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns the task name.
     *
     * @return Task name.
     */
    public String getTaskName() {
        return name;
    }

    /**
     * Returns true if task is done, false otherwise.
     *
     * @return True or false.
     */
    public boolean isDone() {
        return isDone;
    }
}
