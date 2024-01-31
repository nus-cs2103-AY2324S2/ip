package duke.tasks;

public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructs a task with task name appended, not completed by default.
     *
     * @param n Specified task name.
     */
    public Task(String n) {
        this.taskName = n;
        this.isDone = false;
    }

    /**
     * Constructs a task specifying a name and if it is complete.
     *
     * @param n Specified task name.
     * @param d Specifying if the task is done.
     */
    public Task(String n, boolean d) {
        this.taskName = n;
        this.isDone = d;
    }

    @Override
    public String toString() {
        String d = (this.isDone) ? "X" : " ";
        return String.format("[%s] %s", d, taskName);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the task name.
     *
     * @return Task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns true if task is done, false otherwise.
     *
     * @return True or false.
     */
    public boolean isDone() {
        return this.isDone;
    }
}