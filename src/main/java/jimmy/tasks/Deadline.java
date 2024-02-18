package jimmy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    // this date format displays the date purely in numbers
    private final DateTimeFormatter informalDateFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
    // this date format displays the month in words
    private final DateTimeFormatter formalDateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");

    /**
     * Constructor for jimmy.tasks.Deadline class.
     *
     * @param taskName Name of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String taskName, String deadline, boolean isCompleted)
            throws DateTimeParseException, IllegalArgumentException {
        super(taskName, isCompleted);
        this.deadline = this.parseStringtoLocalDate(deadline);

        if (this.deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Converts date given by the user to LocalDate object.
     *
     * @param dateString Date given by the user.
     * @return Date given by the user as LocalDate object.
     */
    private LocalDate parseStringtoLocalDate(String dateString) throws DateTimeParseException {
        LocalDate date;
        date = LocalDate.parse(dateString, informalDateFormat);
        return date;
    }

    /**
     * Converts LocalDate object to String.
     *
     * @param localDate LocalDate object.
     * @return String representation of the LocalDate object.
     */
    private String parseLocalDatetoString(LocalDate localDate) {
        return localDate.format(formalDateFormat);
    }

    /**
     * Details regarding the deadline.
     *
     * @return String representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseLocalDatetoString(this.deadline) + ")";
    }

    /**
     * Format of the deadline to be saved in the file.
     *
     * @return String representation of a deadline.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s", "D", Objects.equals(super.getStatus(), "X") ? 1 : 0,
                super.getDesc(), this.deadline.format(informalDateFormat));
    }
}
