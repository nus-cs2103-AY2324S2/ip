package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final String EVENT_MESSAGE = "[E]%s (from: %s to: %s)";
    private static final String EVENT_FILE_TEMPLATE = "E | %s | %s | %s";
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final DateTimeFormatter returnFormatter = DateTimeFormatter.ofPattern("EEE HHmm dd/MM/yyyy");

    public Event(String description, String startTime, String endTime) {
        super(description);
        DateTimeFormatter acceptFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        this.startTime = LocalDateTime.parse(startTime, acceptFormatter);
        this.endTime = LocalDateTime.parse(endTime, acceptFormatter);
    }

    public String taskFileTemplate() {
        return String.format(EVENT_FILE_TEMPLATE, super.taskFileTemplate(), startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format(EVENT_MESSAGE, super.toString(), startTime.format(returnFormatter), endTime.format(returnFormatter));
    }
}
