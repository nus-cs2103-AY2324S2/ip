package tasks;

public class Event extends Task {
    protected String startString;
    protected String endString;

    public Event(String description, String start, String end) {
        super(description);
        this.startString = start;
        this.endString = end;
    }

    public Event(String description, String start, String end, String isDone) {
        super(description);
        this.startString = start;
        this.endString = end;
        
        if(isDone.equals("0")){
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "]" + super.toString() + " (from: " + startString + "to: " + endString + ")" ; 
    }

    @Override
    public String toStore() {
        return "E" + " | " + super.getStatusInteger() + " | " + super.toString() + " | " + startString + endString + "\n"; 
    }

    
}
