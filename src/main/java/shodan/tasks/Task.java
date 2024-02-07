package shodan.tasks;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String name;
    private boolean isDone = false;

    protected static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("y-LLL-d HH:mm");

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getName() {
        return this.name;
    }
}
