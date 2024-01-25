public class Task {
    public String taskname;
    public boolean marked;

    // Basic Constructor
    public Task(String taskname) {
        this.taskname = taskname;
        this.marked = false;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString() {
        return (this.marked ? "[X] " : "[ ] ") + this.taskname;
    }
}
