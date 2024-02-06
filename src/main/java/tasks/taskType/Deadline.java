package tasks.taskType;

public class Deadline extends Task {

    public Deadline(String task, String type, boolean isDone) {
        super(task, type, isDone);
        // this.deadline = deadline;
    }

    public String formatByDtPattern(String fullCommand) {
        int byStartIndex = fullCommand.indexOf("/by");
        String date = fullCommand.substring(byStartIndex + 4);
        // formatDates function in Task.java
        return this.formatDates(date);
    }

    public String getDescription(String fullCommand) {
        int descIndex = fullCommand.indexOf("/by");
        return fullCommand.substring(9, descIndex - 1);
    }

    @Override
    public String toString() {
        // format string properly
        return super.toString() + getDescription(this.getTask())
                + " (by: " + formatByDtPattern(this.getTask())
                + ")";
    }
}
