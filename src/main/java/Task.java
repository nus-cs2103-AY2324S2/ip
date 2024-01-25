public class Task {
    protected String taskDescription;
    protected boolean isCompleted;
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public void markAsComplete() {
        this.isCompleted = true;
    }
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }
    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }
}
