package hal.task;

public class Todo extends Task {
    String DIVIDER = " | ";

    public Todo(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }

    @Override
    public String getFileString() {
        return "T" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description;
    }
}
