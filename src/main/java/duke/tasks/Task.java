package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Task {
    protected static final String INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";
    protected static final String OUTPUT_DATE_TIME_FORMAT = "MMM dd yyyy hh:mm a";
    public String name;
    private boolean isDone;

    Task(String name, boolean done) {
        this.name = name;
        this.isDone = false;
    }

    Task(String name) {
        this(name, false);
    }

    public String getName() {
        return this.checkbox() + " " + this.name;
    }

    public String typeOfTask() {
        return " ";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String checkbox() {
        return String.format("[%s][%s]", this.typeOfTask(), this.getStatusIcon());
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isMarked() {
        return this.isDone;
    }

    private int isMarkedAsInt() {
        return (this.isDone) ? 1 : 0;
    }

    protected ArrayList<String> exportDataAsArray() {
        ArrayList<String> data = new ArrayList<>(
            Arrays.asList((String) this.typeOfTask(), this.name, Integer.toString(this.isMarkedAsInt())));
        return data;
    }

    public String exportData() {
        String data = String.join("|", this.exportDataAsArray());
        return data;
    }

    public LocalDateTime parseDate(String text, boolean useCustomFormatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT);
        return useCustomFormatter ? LocalDateTime.parse(text, formatter) : LocalDateTime.parse(text);
    }
}
