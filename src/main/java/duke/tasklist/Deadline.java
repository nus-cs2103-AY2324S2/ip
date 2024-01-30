package duke.tasklist;

import duke.exception.MalformedUserInputException;
import duke.common.DateTimeHandler;

public class Deadline extends Task {

    private DateTimeHandler by;

    public Deadline(String description, String by, boolean isDone) throws MalformedUserInputException {
        super(description, isDone);
        this.by = new DateTimeHandler(by);
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
