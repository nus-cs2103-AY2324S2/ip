package eve.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

        if (super.tag == null) {
            return "[E]" + "[" + super.getStatusIcon() + "]"
                    + super.toString() + " (from: " + startString.format(formatter)
                    + " to: " + endString.format(formatter) + ")";
        } else {
            return "[E]" + "[" + super.getStatusIcon() + "]"
            + super.toString() + " (from: " + startString.format(formatter)
            + " to: " + endString.format(formatter) + ")" + super.tag;
        }
    }

    @Override
    public String toStore() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        if(super.tag == null) {
        return "E" + " | " + super.getStatusInteger() + " | "
                + super.toString() + " | " + startString.format(formatter) + " | "
                + endString.format(formatter) + "\n";
        } else {
            return "E" + " | " + super.getStatusInteger() + " | "
                + super.toString() + " | " + startString.format(formatter) + " | "
                + endString.format(formatter) + "#" + super.tag + "\n";    
        }
    }

    public String getStartString() {
        return startString.toString();
    }
    
    public String getEndString() {
        return endString.toString();
    }
}
