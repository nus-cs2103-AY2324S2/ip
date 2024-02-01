package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public void updateTask(boolean status) {
        if (status == true){
            isDone = true;
        } else {
            isDone = false;
        }
    }
}
