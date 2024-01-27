package task;

import util.CSVUtil;

public abstract class Task {
    String description;
    boolean isMarked;

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

    public abstract CSVUtil format();

    @Override
    public String toString() {
        String check = isMarked ? "X" : " ";
        return String.format("[%s] %s", check, description);
    }
}
