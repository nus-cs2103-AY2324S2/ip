package seedu.chatteroo.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //getter for status icon
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //mark task as done
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotdone() {
        this.isDone = false;
    }

    //getter for description
    public String getDescription() {
        return this.description;
    }

    //overridden toString method to print status of task and description
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    public String getTaskType() {
        return this.taskType;
    }

    public boolean getIsDone() {
        return this.isDone;
    }
}
