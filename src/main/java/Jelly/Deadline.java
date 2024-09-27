package jelly;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Deadline task
 */
public class Deadline extends Task {

    private String deadline;
    private LocalDate deadlineDate;
    private boolean isParsed = false;

    /**
     * @param name     Name of deadline
     * @param deadline date/time of deadline
     * @param isDone   whether task is done
     */
    public Deadline(String name, String deadline, boolean isDone) {

        super(name, isDone);
        this.deadline = deadline;
        try {
            this.deadlineDate = LocalDate.parse(deadline);
            this.isParsed = true;
        } catch (DateTimeParseException e) {

            System.out.println("Date cannot be parsed, stored as String instead.");
        }
    }

    @Override
    public String toString() {
        if (isParsed) {

            return "[D]" + super.toString() + "(by: " + deadlineDate + ")";
        }
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String header() {

        int binary = super.isDone ? 1 : 0;
        return this.type() + binary;
    }

    @Override
    public String type() {

        return "D";
    }

    @Override
    public String additionalInfo() {

        return "/" + deadline;
    }

    /**
     * @return Returns whether the deadline has a valid date
     */
    public boolean hasValidDate() {

        return isParsed;
    }
}
