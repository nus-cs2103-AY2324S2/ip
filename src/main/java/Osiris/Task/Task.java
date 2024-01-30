package Osiris.Task;

public abstract class Task {

    private String taskName;

    private boolean isCompleted = false;

    public Task(String taskName){
        this.taskName = taskName;
    }

    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }

    public String getStringStorageRepresentation() {
        String completionStatus = isCompleted ? "Y" : "N";
        return String.format("%s | %s", completionStatus, taskName);
    }

    @Override
    public String toString() {
        String completionStatus = isCompleted ? "[X]" : "[ ]";
        return String.format("%s %s", completionStatus, this.taskName);
    }
}
