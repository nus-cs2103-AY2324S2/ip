public class Task {
    String name;
    String type;
    boolean complete;
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.complete = false;
    }
    String getType() {
        return this.type;
    }
    String getName() {
        return this.name;
    }
    boolean status() {
        return this.complete;
    }
    void mark() {
        this.complete = true;
    }
    void unmark() {
        this.complete = false;
    }
}