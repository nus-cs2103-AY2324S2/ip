public class Event extends Task {
    private String starttime;
    private String endtime;
    public Event(String d, int s, String st, String et) {
        super(d, s);
        this.starttime = st;
        this.endtime = et;
    }

    public String getTime() {
        return "   anytime from " + this.starttime + " to " + this.endtime;
    }

    public String getStartTime() {
        return "   starting: " + this.starttime;
    }

    public String getEndTime() {
        return "   ending: " + this.endtime;
    }

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "   Well done! Event: " + this.getDesc() + " finished.";
        } else {
            return "   Event updated. Event: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[D] [ ] " + this.getDesc() + " (from: " + this.starttime + ", to: " + this.endtime + ")";
        } else {
            return "[D] [X] " + this.getDesc() + " (from: " + this.starttime + ", to: " + this.endtime + ")";
        }
    }
}
