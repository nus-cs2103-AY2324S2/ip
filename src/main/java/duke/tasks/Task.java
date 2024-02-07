package duke.tasks;

public abstract class Task {
    protected boolean isDone;
    protected String taskDescription;

    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean match(String s) {
        return taskDescription.toLowerCase().contains(s);
    }

    public abstract String toFileString();
}
