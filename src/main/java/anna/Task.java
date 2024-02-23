package anna;

import java.time.format.DateTimeFormatter;

/**
 * Task abstract class
 */
public abstract class Task {

    protected static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(
        "d/M/yyyy HH:mm"
    );
    protected static final DateTimeFormatter DATETIME_PRINT_FORMAT = DateTimeFormatter.ofPattern(
        "MMM dd yyyy HH:mm"
    );
    protected static final DateTimeFormatter LOCALTIME_FORMAT = DateTimeFormatter.ofPattern(
        "HH:mm:ss"
    );
    protected boolean done;
    protected TaskID id;
    protected String task;

    Task(String task, boolean done, TaskID id) {
        this.done = done;
        this.id = id;
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String taskId() {
        return id.toString();
    }

    public String toString() {
        return String.format("[%s][%s] %s", id, done ? "X" : " ", taskStr());
    }

    public String serialise() {
        return String.format("%s<0>%s<0>%s<1>", id, task, done ? "X" : " ");
    }

    public abstract String taskStr();
}
