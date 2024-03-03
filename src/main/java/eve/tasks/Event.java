package eve.tasks;

import java.time.LocalDateTime;

import eve.parser.Parser;

/*
 * Event class is a subclass of Task class
 */
public class Event extends Task {
    protected LocalDateTime startString;
    protected LocalDateTime endString;

    public Event(String description, String start, String end) {
        super(description);
        this.startString = Parser.stringToDateTime(start);
        this.endString = Parser.stringToDateTime(end);
    }

    public Event(String description, String start, String end, String isDone) {
        super(description);
        this.startString = Parser.stringToDateTime(start);
        this.endString = Parser.stringToDateTime(end);
        
        if (isDone.equals("0")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }



    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "]"
                + super.toString() + " (from: " + startString
                + "to: " + endString + ")" + super.tag;
    }

    @Override
    public String toStore() {
        return "E" + " | " + super.getStatusInteger() + " | "
                + super.toString() + " | " + startString
                + endString + "\n" + super.tag;
    }

    public String getStartString() {
        return startString.toString();
    }
    
    public String getEndString() {
        return endString.toString();
    }
}
