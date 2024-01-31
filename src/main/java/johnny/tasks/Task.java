package johnny.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private String name;
    private boolean isDone = false;


    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String x = isDone ? "X" : " ";
        return "[" + x + "] " + name;
    }

    public String addToFile() {
        String isDone = this.isDone ? "1" : "0";
        return isDone + " | " + name;
    }

    public String formatInputDate(LocalDateTime dateTime) {
        return dateTime.format(INPUT_DATE_FORMAT);
    }

    public String formatOutputDate(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DATE_FORMAT);
    }

    /**
     * Checks if Task name contains keyword.
     *
     * @param keyword String to be matched to Task name.
     * @return True if name contains else false;
     */
    public boolean contains(String keyword) {
        return name.contains(keyword);
    }

}
