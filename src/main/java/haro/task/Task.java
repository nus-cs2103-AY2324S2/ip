package haro.task;

public abstract class Task {
    protected String task;
    protected boolean done;
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public void markTask() {
        this.done = true;
    }

    public void unmarkTask() {
        this.done = false;
    }

    public String printTask() {
        String taskString;
        if (this.done) {
            taskString = "[X] " + this.task;
        } else {
            taskString = "[ ] " + this.task;
        }

        return taskString;
    }

}
