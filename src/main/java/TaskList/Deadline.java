package TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;

import CustomExceptions.MalformedUserInputException;
import Parser.DateTimeParser;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, String by, boolean isDone) throws MalformedUserInputException {
        super(description, isDone);
        this.by = DateTimeParser.getDateTimeFromString(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + this.getDescription() + " | " + super.getStatus() + " | " + this.by;
    }


}
