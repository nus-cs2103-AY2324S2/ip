import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Task {    
    public static int EXPECTED_FIELDS = 3;
    protected static String INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";
    protected static String OUTPUT_DATE_TIME_FORMAT = "MMM dd yyyy hh:mm a";
    public String name;
    public boolean done;

    Task(String name, boolean done) {
        this.name = name;
        this.done = false;
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
        return (done ? "X" : " "); // mark done task with X
    }

    public String checkbox() {
        return String.format("[%s][%s]", this.typeOfTask(), this.getStatusIcon());
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isMarked() {
        return this.done;
    }

    private int isMarkedAsInt() {
        return (this.done) ? 1 : 0;
    }

    protected ArrayList<String> exportDataAsArray() {
        ArrayList<String> data = new ArrayList<>(Arrays.asList((String)this.typeOfTask(), this.name, Integer.toString(this.isMarkedAsInt())));
        return data;
    }

    public String exportData() {
        String data = String.join("|", this.exportDataAsArray());
        return data;
    }

    public LocalDateTime parseDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT);
        return LocalDateTime.parse(text, formatter);
    }
}