public class Event extends Task {
    String startTime;
    String endTime;
    public Event(String name, String startTime, String endTime) {
        super(name, "E");
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String toString() {
        String status = this.complete ? "[x] " : "[ ]";
        return "[E] " + status + " " + this.name + " (" + this.startTime + this.endTime + ")";
    }
}

