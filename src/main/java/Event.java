public class Event extends Task {
    private final String type = "[E]";
    private String start;
    private String end;


    public Event(String description) {
        super(description.split("/")[0]);

        int fromId = description.indexOf("/from");
        int toId = description.indexOf("/to");
        this.start = description.substring(fromId +6, toId);
        this.end  = description.substring(toId +4);


    }
    public String getType() {
        return this.type;
    }

    public String getExtraInfo() {
        return "(from: " + this.start + "to: " + this.end + ")";
    }
}
