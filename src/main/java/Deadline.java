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
            return "\tWell done! Deadline: " + this.getDesc() + " beaten.";
        } else {
            return "\tDeadline updated. Deadline: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus()) + "," + this.getDesc();
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
