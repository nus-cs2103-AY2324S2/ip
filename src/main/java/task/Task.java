package task;
import util.CsvUtil;

public abstract class Task {
    protected final String description;
    protected boolean isMarked;

    Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    Task(boolean isMarked, String description) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public Task mark() {
        this.isMarked = true;
        return this;
    }

    public Task unmark() {
        this.isMarked = false;
        return this;
    }

    public abstract CsvUtil format();

    @Override
    public String toString() {
        String check = isMarked ? "X" : " ";
        return String.format("[%s] %s", check, description);
    }

    public boolean contains(String s) {
        return this.description.contains(s);
    }
}
