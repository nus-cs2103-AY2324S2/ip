package tasks;

/**
 * This is an abstract class representing a task by encapsulating information, such as the description
 * of the task, and performing basic functions involving the task.
 */

public abstract class Task {
    public final String taskName;
    protected boolean isCompleted;

    public Task(String task) {
        this.taskName = task;
    }

    public void markTask() {
        this.isCompleted = true;
    }
    public void unmarkTask() {
        this.isCompleted = false;
    }
    public abstract String saveFormat();
    @Override
    public String toString() {
        String check = isCompleted ? "[X]" : "[ ]";
        return check + " " + this.taskName;
    }
}
