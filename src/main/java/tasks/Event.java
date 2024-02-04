package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;
    private final DateTimeFormatter informalDateFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
    private final DateTimeFormatter formalDateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");

    /**
     * Constructor for tasks.Event class.
     *
     * @param taskName Name of the task.
     * @param start    Start time of the event.
     * @param end      End time of the event.
     */
    public Event(String taskName, String start, String end, boolean isCompleted) throws DateTimeParseException, IllegalArgumentException {
        super(taskName, isCompleted);
        this.start = parseStringtoLocalDate(start);
        this.end = parseStringtoLocalDate(end);
        if (!checkValidDuration(this.start, this.end)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if the start and end timing given by the user are valid
     *
     * @param start Start time of the event.
     * @param end   End time of the event.
     * @return true if the start and end timing are valid, false otherwise.
     */
    private boolean checkValidDuration(LocalDate start, LocalDate end) {
        boolean isStartBeforeEnd = start.isBefore(end);
        boolean isStartAfterNow = start.isAfter(LocalDate.now());
        boolean isEndAfterNow = end.isAfter(LocalDate.now());
        return isStartBeforeEnd && isStartAfterNow && isEndAfterNow;
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
     * Details regarding the event.
     *
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + parseLocalDatetoString(this.start) + " to: " + parseLocalDatetoString(this.end) + ")";
    }

    /**
     * Format of the event to be saved in the file.
     *
     * @return String representation of an event.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s | %s", "E", Objects.equals(super.getStatus(), "X") ? 1 : 0, super.getDesc(), this.start.format(informalDateFormat), this.end.format(informalDateFormat));
    }
}
