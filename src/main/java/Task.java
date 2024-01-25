import java.util.*;
public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;

    public Task(int index, String description) {
        this.index = index+1;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTask() {
        return index + ". " + description;
    }
}
