import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Task {

    private String desc;
    private boolean isDone;

    // Additional fields for different task types
    private String type;

    private String start, end;

    HashMap<String, Character> map = new HashMap<>();

    private void initMap() {
        map.put("ToDo", 'T');
        map.put("Deadline", 'D');
        map.put("Event", 'E');

    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = "ToDo"; // Default type is ToDo
        initMap();
    }

    // Constructor for Deadline task
    public Task(String desc, boolean isDone, String  start) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = "Deadline";
        this.start = start;
        initMap();
    }

    // Constructor for Event task
    public Task(String desc, boolean isDone, String start, String end) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = "Event";
        this.start = start;
        this.end = end;
        initMap();
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        taskString.append("[" + map.get(type) + "]");
        taskString.append(isDone ? "[X]" : "[ ]");

        // Append task description
        taskString.append(" ").append(desc);

        // Append date/time information if available
        if (type.equals("Deadline")) {
            taskString.append(" (");
            taskString.append(start);
            taskString.append(")");
        }
        if (type.equals("Event")) {
            taskString.append(" (");
            taskString.append(start).append(" ");
            taskString.append(end);
            taskString.append(")");
        }

        return taskString.toString();
    }

    // Helper method to format LocalDateTime
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return dateTime.format(formatter);
    }
}
