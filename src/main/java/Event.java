public class Event extends Task {
    private final String TYPE = "E";
    private String start;
    private String end;


    public Event(String description) {
        super(description.split("/")[0]);

        int fromId = description.indexOf("/from");
        int toId = description.indexOf("/to");
        this.start = description.substring(fromId +6, toId);
        this.end  = description.substring(toId +4);

    }

    public Event(String event, String extraInfo) {
        super(event);
        String[] x = extraInfo.split("-");
        this.start = x[0];
        this.end = x[1];
    }
    public String getType() {
        return this.TYPE;
    }
    public String getExtraInfoShortened() {

        return this.start + "-" + this.end;
    }


    public String getExtraInfo() {
        return " (from: " + this.start + " to: " + this.end + ")";
    }
}
