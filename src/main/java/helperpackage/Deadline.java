package helperpackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    DateTimeFormatter receivingFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    DateTimeFormatter printingFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    
    public Deadline(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/")).trim());
        int indexOfBy = description.indexOf("/by");

        if (indexOfBy != -1 && indexOfBy + 4 < description.length()) {
            try {
                deadline = LocalDateTime.parse(description.substring(indexOfBy + 4), receivingFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("Date and Time not in the correct format.\n"
                        + "Correct format: dd/MM/yyyy HHmm\n"
                        + "Received: " + description + "\n"
                        + "Deadline not added to the list.");
            }
        } else {
            throw new DukeException("Invalid deadline input. :(");
        }
    }

    public Deadline(boolean isDone, String description, String deadline) throws DukeException {
        super(isDone, description);
        try {
            this.deadline = LocalDateTime.parse(deadline, printingFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date and Time not in the correct format.\n"
                    + "Correct format: MMM dd yyyy, HH:mm\n"
                    + "Received: " + description + " | " + deadline + "\n"
                    + "Deadline removed from the list.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(printingFormatter) + ")";
    }

    @Override
    public String toData() {
        return "D | " + super.toData() + " | " + deadline.format(printingFormatter); 
    }
}