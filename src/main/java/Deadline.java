/**
 * Contains logic for the Deadline class that extends the abstract Task Class
 * Only has an end time
 */
public class Deadline extends Task {
    String endDateTime;

    Deadline (String deadlineName, String endDateTime) {
        this.taskName = deadlineName;
        this.completed = false;
        this.endDateTime = endDateTime;
    }

    Deadline (String deadlineName, Boolean completed, String endDateTime) {
        this.taskName = deadlineName;
        this.completed = completed;
        this.endDateTime = endDateTime;
    }

    public void markComplete() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String xMarker = this.completed ? "[X]" : "[ ]";
        return String.format("[D] %s %s (by: %s)", xMarker, this.taskName, this.endDateTime);
    }
}
