public class Event extends Task {
    private String starttime;
    private String endtime;
    public Event(String d, int s, String st, String et) {
        super(d, s);
        this.starttime = st;
        this.endtime = et;
    }

    public String getTime() {
        return "\tanytime from " + this.starttime + " to " + this.endtime;
    }

    public String getStartTime() {
        return "\tstarting: " + this.starttime;
    }

    public String getEndTime() {
        return "\tending: " + this.endtime;
    }

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "\tWell done! Event: " + this.getDesc() + " finished.";
        } else {
            return "\tEvent updated. Event: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus()) + "," + this.getDesc();
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[D] [ ] " + this.getDesc() + " (from:" + this.starttime + ", to:" + this.endtime + ")";
        } else {
            return "[D] [X] " + this.getDesc() + " (from:" + this.starttime + ", to:" + this.endtime + ")";
        }
    }
}
