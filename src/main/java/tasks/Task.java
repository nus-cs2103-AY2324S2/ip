package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy EEEE, ha");
    private String name;
    private String type;
    private boolean isDone;
    private String priority;
    private String[] times;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.priority = "NONE";
    }

    public Task(String name, boolean isDone, String priority) {
        this.name = name;
        this.isDone = isDone;
        this.priority = priority;
    }

    public boolean toggleMarkStatus(boolean isMark) {
        if (this.isDone == isMark) {
            return false;
        }
        this.isDone = isMark;
        return true;
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

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String outputDateAsString(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMAT);
    }

    public String convertDateToString(LocalDateTime dateTime) {
        return dateTime.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "][Priority: " + this.priority + "] " + this.name;
    }
}
