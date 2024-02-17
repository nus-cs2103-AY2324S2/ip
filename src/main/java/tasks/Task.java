package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy EEEE, ha");
    private String name;
    private boolean isDone;
    private String type;
    private String[] times;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void toggleMarkStatus() {
        this.isDone ^= true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getType() {
        return this.type;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String[] getTimes() {
        return this.times;
    }

    public String getName() {
        return this.name;
    }

    public String outputDateAsString(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMAT);
    }

    public String convertDateToString(LocalDateTime dateTime) {
        return dateTime.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
