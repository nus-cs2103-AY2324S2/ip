public class Deadline extends Task {
    String endDateTime;

    Deadline (String deadlineName) {
        this.taskName = deadlineName;
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
        return String.format("[D] %s %s", xMarker, this.taskName);
    }
}
