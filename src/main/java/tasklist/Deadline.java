package tasklist;

import exception.MalformedUserInputException;
import parser.DateTimeParser;

public class Deadline extends Task {

    private DateTimeParser by;

    public Deadline(String description, String by, boolean isDone) throws MalformedUserInputException {
        super(description, isDone);
        this.by = new DateTimeParser(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + this.getDescription() + " | " + super.getStatus() + " | " + this.by.toStorageString();
    }


}
