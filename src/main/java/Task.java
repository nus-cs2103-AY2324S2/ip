public class Task {

    private String taskName;

    private boolean isCompleted = false;

    public Task(String taskName){
        this.taskName = taskName;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
