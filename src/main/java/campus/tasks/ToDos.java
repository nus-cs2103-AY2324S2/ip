package campus.tasks;

/**
 * Contains the logic for the ToDos Class which extends the abstract Task Class.
 */
public class ToDos extends Task {
    public ToDos(String TodoTask) {
        this.taskName = TodoTask;
        this.completed = false;
    }

    public ToDos (String TodoTask, Boolean completed) {
        this.taskName = TodoTask;
        this.completed = completed;
    }

    @Override
    public void markComplete() {
        this.completed = true;
    }

    @Override
    public void markIncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String xMarker = this.completed ? "[X]" : "[ ]";
        return String.format("[T] %s %s", xMarker, this.taskName);
    }

    @Override
    public String toDBFormat() {
        String completed = this.completed ? "1" : "0";
        return String.format("T | %s | %s", completed, this.taskName);
    }
}
