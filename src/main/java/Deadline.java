public class Deadline extends Task {
    private String time;
    public Deadline(String d, int s, String t) {
        super(d, s);
        this.time = t;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "   Well done! Deadline: " + this.getDesc() + " beaten.";
        } else {
            return "   Deadline updated. Deadline: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[D] [ ] " + this.getDesc() + " (by:" + this.time + ")";
        } else {
            return "[D] [X] " + this.getDesc() + " (by:" + this.time + ")";
        }
    }
}
