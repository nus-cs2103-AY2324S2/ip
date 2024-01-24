import java.util.ArrayList;

public class Task {
    protected final String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }
    
    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }

    public String describe() {
        return "[" + (this.done ? "X": " ") + "] " + this.name;
    }    
}
