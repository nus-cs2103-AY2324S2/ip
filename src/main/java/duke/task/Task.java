package duke.task;

public class Task {
    String name;
    String type;
    boolean complete;
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.complete = false;
    }
    public String getType() {
        return this.type;
    }
    public String getName() {
        return this.name;
    }
    public boolean status() {
        return this.complete;
    }
    public void mark() {
        this.complete = true;
    }
    public void unmark() {
        this.complete = false;
    }
}