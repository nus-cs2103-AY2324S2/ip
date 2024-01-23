public class Event extends Task {
    String startDateTime;
    String endDateTime;

    Event (String eventName) {
        this.taskName = eventName;
        this.completed = false;
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
        return String.format("[E] %s %s", xMarker, this.taskName);
    }
}
