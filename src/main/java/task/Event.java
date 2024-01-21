package task;

public class Event extends Task {
    private final String from;
    private final String to;
    public Event(String msg) {
        super();
        String[] res = msg.split(" ((/from)|(/to)) ");
        this.msg = res[0];
        this.from = res[1];
        this.to = res[2];
    }

    @Override
    public String toString() {
        String is_done = this.done ? "X" : " ";
        return String.format("[E][%s] %s (from: %s to: %s)", is_done, this.msg, this.from, this.to);
    }
}
