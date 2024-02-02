package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String taskDescription, boolean isCompleted, LocalDateTime by) {
        super(taskDescription, isCompleted);
        this.by = by;
    }

    public String getDeadlineDescription() {
        return trimDescription(taskDescription);
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String getTaskIcon() {
        return "[D]";
    }

    @Override
    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    @Override
    public String getTaskDescription() {
        return trimDescription(taskDescription) + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    protected String trimDescription(String taskDescription) {
        String regex = "(?i)deadline\\s*(.*?)\\s*/by\\s*(.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDescription);

        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            by = LocalDateTime.parse(matcher.group(2), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        }

        return taskDescription;
    }


}
