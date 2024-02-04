package haro.task;

public abstract class Task {
    protected String task;
    protected boolean isDone;
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String printTask() {
        String taskString;
        if (this.isDone) {
            taskString = "[X] " + this.task;
        } else {
            taskString = "[ ] " + this.task;
        }

        return taskString;
    }

}
