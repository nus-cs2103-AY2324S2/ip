public class Task {
    private boolean isDone;
    private int taskIndex;
    private String taskDescription;

    public Task(int taskIndex, String taskDescription) {
        this.isDone = false;
        this.taskIndex = taskIndex;
        this.taskDescription = taskDescription;
    }

    public void setTaskIndex(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.taskDescription);
    }
}
