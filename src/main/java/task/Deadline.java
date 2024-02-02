package task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private String by;

    public Deadline(String taskDescription, boolean isCompleted, String by) {
        super(taskDescription, isCompleted);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public String getDescription() {
        return trimDescription(taskDescription);
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
        return getDescription() + " (by: " + by + ")";
    }

   @Override
    protected String trimDescription(String taskDescription) {
        String regex = "(?i)deadline\\s*(.*?)\\s*/by\\s*(.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDescription);

        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            by = matcher.group(2).trim();
        }

        return taskDescription;
    }
}
