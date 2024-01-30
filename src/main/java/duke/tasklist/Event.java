package duke.tasklist;

import duke.exception.MalformedUserInputException;
import duke.common.DateTimeHandler;

public class Event extends Task {
    private DateTimeHandler startTime;
    private DateTimeHandler endTime;

    public Event(String description, String startTime, String endTime, boolean isDone) throws MalformedUserInputException {
        super(description, isDone);
        this.startTime = new DateTimeHandler(startTime);
        this.endTime = new DateTimeHandler(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String toStorageString() {
        return "E | " + this.getDescription() + " | " + super.getStatus() + " | " + this.startTime.toStorageString() + " | " + this.endTime.toStorageString();
    }


}
