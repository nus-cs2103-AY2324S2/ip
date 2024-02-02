package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String taskDescription, boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(taskDescription, isCompleted);
        this.from = from;
        this.to = to;
    }

    public String getEventDescription() {
        return trimDescription(taskDescription);
    }


    @Override
    public String getTaskIcon() {
        return "[E]";
    }

    @Override
    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    @Override
    public String getTaskDescription() {
        return trimDescription(taskDescription) + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    protected String trimDescription(String taskDescription) {
        // Used regex to remove "event", "/from", and "/to" and extracted "from" and "to" details
        String regex = "(?i)event\\s*(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDescription);

        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            String fromString = matcher.group(2).trim();
            String toString = matcher.group(3).trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            from = LocalDateTime.parse(fromString, formatter);
            to = LocalDateTime.parse(toString, formatter);
        }

        return taskDescription;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }
}
