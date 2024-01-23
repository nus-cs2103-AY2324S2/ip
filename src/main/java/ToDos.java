public class ToDos extends Task {
    ToDos (String TodoTask) {
        this.taskName = TodoTask;
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
        return String.format("[T] %s %s", xMarker, this.taskName);
    }
}
