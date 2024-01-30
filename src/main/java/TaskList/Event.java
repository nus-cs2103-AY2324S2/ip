package TaskList;

import CustomExceptions.MalformedUserInputException;
import Parser.DateTimeParser;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String description, String startTime, String endTime, boolean isDone) throws MalformedUserInputException {
        super(description, isDone);
        this.startTime = DateTimeParser.getDateTimeFromString(startTime);
        this.endTime = DateTimeParser.getDateTimeFromString(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String toStorageString() {
        return "E | " + this.getDescription() + " | " + super.getStatus() + " | " + this.startTime + " | " + this.endTime;
    }


}
