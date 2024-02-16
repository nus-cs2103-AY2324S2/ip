package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import common.DukeException;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    DateTimeFormatter receivingFormatter = DateTimeFormatter.ofPattern("d/M/yyyy-HHmm");
    DateTimeFormatter printingFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public Deadline(String taskName, String deadline) throws DukeException {
        super(taskName);
        try {
            this.deadline = LocalDateTime.parse(deadline, receivingFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date and Time not in the correct format.\n"
                    + "Correct format: dd/MM/yyyy-HHmm\n"
                    + "Received: " + deadline + "\n"
                    + taskName + " not added to the list.");
        }
    }

    public Deadline(boolean isDone, String taskName, String deadline) throws DukeException {
        super(isDone, taskName);
        try {
            this.deadline = LocalDateTime.parse(deadline, printingFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date and Time not in the correct format.\n"
                    + "Correct format: MMM dd yyyy, HH:mm\n"
                    + "Received: " + taskName + " | " + deadline + "\n"
                    + taskName + " removed from the list.");
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