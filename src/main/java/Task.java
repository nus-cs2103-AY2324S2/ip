import java.sql.Array;
import java.util.ArrayList;

public class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return this.done
            ? "[X] " + this.taskName
            : "[ ] " + this.taskName;
    }
}
