package task;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final DateTimeFormatter ORIGINAL_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyyHHmm");
    public Event(String s, String startTimeString, String endTimeString) {
        super(s);

        startTimeString = startTimeString.replaceAll("\\s", "");
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("d/M/yyyyHHmm");
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, originalFormatter);

        endTimeString = endTimeString.replaceAll("\\s", "");
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, originalFormatter);

        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                this.startTime.format(ORIGINAL_FORMATTER),
                this.endTime.format(ORIGINAL_FORMATTER));
    }

    @Override
    public String convertToDataStoreLine() {
        return String.format("E|%s|%s|%s|%s", super.convertToDataStoreLine(),
                super.getTaskString(), this.startTime, this.endTime);
    }
}