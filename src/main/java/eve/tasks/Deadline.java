package eve.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import eve.parser.Parser;
/*
 * Deadline class is a subclass of Task class
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String by) {
        super(description);
        LocalDateTime temp = Parser.stringToDateTime(by);
        this.by = temp;
    }

    public Deadline(String description, String by, String isDone) {
        super(description);
        LocalDateTime temp = Parser.stringToDateTime(by);
        this.by = temp;
        if (isDone.equals("0")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public LocalDateTime getDeadline() {
        return by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        
        if (super.tag == null){
            return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by.format(formatter) + ")";
        } else  {
            return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by.format(formatter) + ")" + "#" + super.tag;
        }

    }
    @Override
    public String toStore() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        if (super.tag == null) {
            return "D" + " | " + super.getStatusInteger() + " | " + super.toString() + " | " + by.format(formatter) + "\n";
        } else {
            return "D" + " | " + super.getStatusInteger() + " | " + super.toString() + " | " + by.format(formatter) + "#"
                    + super.tag + "\n";
        }
    }
}
