package TaskList;

import CustomExceptions.MalformedUserInputException;
import Parser.DateTimeParser;

import java.time.LocalDate;

public class Event extends Task {
    private DateTimeParser startTime;
    private DateTimeParser endTime;

    public Event(String description, String startTime, String endTime, boolean isDone) throws MalformedUserInputException {
        super(description, isDone);
        this.startTime = new DateTimeParser(startTime);
        this.endTime = new DateTimeParser(endTime);
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
