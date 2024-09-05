package aaron.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import aaron.exception.AaronBotException;
import aaron.exception.DateFormatException;

/**
 * class that represents a deadline type of task instantiated in an Aaronbot
 * tasklist
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private LocalDate deadline;

    public Deadline(String taskString, String deadline) throws AaronBotException {
        super(taskString);
        try {
            this.deadline = LocalDate.parse(deadline, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Illegal date format: " + deadline);
        }
    }

    public Deadline(String taskString, String deadline, boolean isDone) throws AaronBotException {
        super(taskString, isDone);
        try {
            this.deadline = LocalDate.parse(deadline, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Illegal date format: " + deadline);
        }
    }

    /**
     * Setter method for altering deadline date for a deadline task
     * 
     * @param postponeAmount amount to postpone task
     */
    public void postpone(int postponeAmount) {
        this.deadline = this.deadline.plusDays(postponeAmount);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " | " + deadline.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) obj;
        return (super.equals(deadline) && this.deadline.equals(deadline.deadline));
    }

}
