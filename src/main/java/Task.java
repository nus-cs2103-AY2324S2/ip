public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String toggleIsDone() {
        isDone = !isDone;
        return getStatusIcon();
    }
    @Override
    public String toString(){
        return description;
    }
    //...
}
