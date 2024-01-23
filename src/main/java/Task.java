public class Task {

    private String taskName;

    private boolean isCompleted = false;

    public Task(String taskName){
        this.taskName = taskName;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String completionStatus = isCompleted ? "[X]" : "[ ]";
        return String.format("%s %s", completionStatus, this.taskName);
    }
}
