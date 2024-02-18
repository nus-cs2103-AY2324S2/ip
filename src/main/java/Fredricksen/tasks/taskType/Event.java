package Fredricksen.tasks.taskType;

public class Event extends Task {
    public Event(String task, String type, boolean isDone) {
        super(task, type, isDone);
    }

    public String formatFromDtPattern(String fullCommand) {
        int fromStartIndex = fullCommand.indexOf("/from");
        int toStartIndex = fullCommand.indexOf("/to");

        assert fromStartIndex != -1 : "Should not happen";
        assert toStartIndex != -1 : "Should not happen";

        String fromDate = fullCommand.substring(fromStartIndex + 6, toStartIndex - 1);
        return this.formatDates(fromDate);
    }

    public String formatToDtPattern(String fullCommand) {
        int toStartIndex = fullCommand.indexOf("/to");
        String toDate = fullCommand.substring(toStartIndex + 4);
        return this.formatDates(toDate);
    }

    public String getDescription(String fullCommand) {
        int descIndex = fullCommand.indexOf("/from");
        return fullCommand.substring(6, descIndex - 1);
    }

    @Override
    public String toString() {
        // format string properly
        return super.toString() + getDescription(this.getTask())
                + " (from: " + formatFromDtPattern(this.getTask())
                + " to: " + formatToDtPattern(this.getTask())
                + ")";
    }
}

