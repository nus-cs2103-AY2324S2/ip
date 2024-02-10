package Aaron.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Aaron.Exception.DateFormatException;
import Aaron.Exception.InvalidDateException;
/**
 * class that represents a deadline type of task instantiated in an Aaronbot tasklist
 */
public class Deadline extends Task{
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private LocalDate deadline;
    
    public Deadline(String taskString, String deadline) throws DateFormatException{
        super(taskString);
        try {
            this.deadline = LocalDate.parse(deadline, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Illegal date format: " + deadline);
        }
    }

    public Deadline(String taskString, String deadline, boolean isDone) throws DateFormatException{
        super(taskString, isDone);
        try {
            this.deadline = LocalDate.parse(deadline, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Illegal date format: " + deadline);
        }
    }

    /**
     * Setter method for altering deadline date for a deadline task
     * @param userString new desired date
     * @throws InvalidDateException if userString is in the wrong format
     */
    public void changeDeadline(String userString) throws InvalidDateException {
        try {
            this.deadline = LocalDate.parse(userString, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Illegal date format: " + deadline);
        }
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
