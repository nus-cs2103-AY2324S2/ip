public class Task {
    public String taskname;
    public boolean marked;
    public boolean hasDate;

    // Basic Constructor
    public Task(String taskname) {
        this.taskname = taskname;
        this.marked = false;
        this.hasDate = false;
    }
    public void mark() {
        this.marked = true;
    }
    public void unmark() {
        this.marked = false;
    }
    public Boolean isWithinDate(DateTime dt) {
        return false;
    }

    @Override
    public String toString() {
        return (this.marked ? "[X] " : "[ ] ") + this.taskname;
    }
}
