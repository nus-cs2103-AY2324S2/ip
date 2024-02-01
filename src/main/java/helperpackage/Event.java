package helperpackage;

public class Event extends Task {
    protected String startAndEndTime;
    
    public Event(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/")));
        String time = description.substring(description.indexOf("/")).replace("/from", 
                "from:").replace("/to", "to:");

        if (!(time.contains("from:") || time.contains("to:"))) {
            throw new DukeException("Invalid event input. :(");
        }
        
        this.startAndEndTime = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" +  startAndEndTime + ")";
    }
}
