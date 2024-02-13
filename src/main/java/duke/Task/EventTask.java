package duke.Task;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalTime startTime;
    private LocalTime endTime;

    public EventTask(String description, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime.format(DateTimeFormatter.ofPattern("HHmm")) + " | " + endTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to: " + endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}