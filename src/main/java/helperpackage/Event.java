package helperpackage;

public class Event extends Task {
    protected String startAndEndTime;
    
    public Event(String description) {
        super(description.substring(0, description.indexOf("/")));
        this.startAndEndTime = description.substring(description.indexOf("/")).replace("/from", 
                "from:").replace("/to", "to:");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" +  startAndEndTime + ")";
    }
}
