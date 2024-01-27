/**
 * Contains the logic for Event that extends the abstract class Task
 * Has both a start and end time
 */
public class Event extends Task {
    String startDateTime;
    String endDateTime;

    Event (String eventName, String startDateTime, String endDateTime) {
        this.taskName = eventName;
        this.completed = false;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    Event (String eventName, Boolean completed, String startDateTime, String endDateTime) {
        this.taskName = eventName;
        this.completed = completed;
        this.startDateTime = startDateTime;
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
        return String.format("[E] %s %s (from: %s to: %s)", xMarker, this.taskName, this.startDateTime, this.endDateTime);
    }
}
