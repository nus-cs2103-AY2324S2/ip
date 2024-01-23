public class Task {
    private String description;
    private boolean isDone = false;

    Task(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X": " "; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }
}
