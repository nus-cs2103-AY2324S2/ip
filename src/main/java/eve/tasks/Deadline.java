package eve.tasks;

import java.time.LocalDateTime;

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
        if (super.tag == null){
            return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
        } else  {
            return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")" + "#" + super.tag;
        }

    }
    @Override
    public String toStore() {
        if(super.tag == null){
            return "D" + " | " + super.getStatusInteger() + " | " + super.toString() + " | " + by;
        } else {
            return "D" + " | " + super.getStatusInteger() + " | " + super.toString() + " | " + by + "\n" + "#" + super.tag;
        }
    }
}
