public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.description;
    }

    //    public String getStatusIcon() {
//        return (isDone ? "X" : " "); // mark done task with X
//    }
}