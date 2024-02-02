package task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String taskDescription, boolean isCompleted, String from, String to) {
        super(taskDescription, isCompleted);
        this.from = from;
        this.to = to;
    }

    public String getDescription() {
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
        return getDescription() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    protected String trimDescription(String taskDescription) {
        // Use regex to remove "event", "/from", and "/to" and extract "from" and "to" details
        String regex = "(?i)event\\s*(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDescription);

        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            from = matcher.group(2).trim();
            to = matcher.group(3).trim();
        }

        return taskDescription;
    }


    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

}
