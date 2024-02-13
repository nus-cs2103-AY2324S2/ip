/**
 * Represents a task with a specific start and end time.
 * This class extends the Task class and adds functionality for tasks with event timings.
 */
package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.InvalidEventException;

/**
 * Class for Event Task
 */
public class Event extends Task {
    private String start;
    private String end;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    /**
     * Constructs an Event object with the given description, start, and end times.
     *
     * @param desc  The description of the event task.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     * @throws InvalidEventException If the end time is before the start time.
     */
    public Event(String desc, String start, String end) throws InvalidEventException {
        super(desc);
        start = start.trim();
        end = end.trim();
        if (isValidDateFormat(start)) {
            String[] dateNumbers = start.split("[/ ]");
            this.startDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.start = start;
        }
        if (isValidDateFormat(end)) {
            String[] dateNumbers = end.split("[/ ]");
            this.endDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.end = end;
        }
        if (this.startDate != null && this.endDate != null && this.endDate.isBefore(this.startDate)) {
            throw new InvalidEventException();
        }
    }

    /**
     * Constructs an Event object with the given status, description, start, and end times.
     *
     * @param status      The status of the event task.
     * @param description The description of the event task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String status, String description, String start, String end) {
        super(description);
        super.setStatus(status);
        start = start.trim();
        end = end.trim();
        if (isValidDateFormat(start)) {
            String[] dateNumbers = start.split("[/ ]");
            this.startDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.start = start;
        }
        if (isValidDateFormat(end)) {
            String[] dateNumbers = end.split("[/ ]");
            this.endDate = LocalDateTime.of(
                    Integer.parseInt(dateNumbers[2]),
                    Integer.parseInt(dateNumbers[1]),
                    Integer.parseInt(dateNumbers[0]),
                    Integer.parseInt(dateNumbers[3].substring(0, 2)),
                    Integer.parseInt(dateNumbers[3].substring(2)));
        } else {
            this.end = end;
        }
    }

    /**
     * Checks if the given deadline string has a valid date format.
     *
     * @param deadline The deadline string to be checked.
     * @return True if the deadline string has a valid format, otherwise false.
     */
    private static boolean isValidDateFormat(String deadline) {
        if (deadline.length() <= 12 || deadline.length() >= 16) {
            return false;
        }
        String[] dateNumbers = deadline.split("[/ ]");
        if (dateNumbers.length != 4) {
            return false;
        }
        try {
            for (String i : dateNumbers) {
                Integer.parseInt(i);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        if (Integer.parseInt(dateNumbers[3]) >= 2400) {
            return false;
        }
        return true;
    }
    /**
     * Writes the Event object into a string format for storage.
     *
     * @return A string representing the Event object.
     */
    @Override
    public String writeObject() {
        if (startDate != null && endDate != null) {
            return String.format("event %s | %s | %s\n",
                    super.writeObject(),
                    this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                    this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        }
        return String.format("event %s | %s | %s \n", super.writeObject(), this.start, this.end);
    }

    /**
     * Converts the Event object into a string representation.
     *
     * @return A string representing the Event object.
     */
    @Override
    public String toString() {
        String startString = startDate != null
                ? this.startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                : this.start;
        String endString = endDate != null
                ? this.endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                : this.end;
        return String.format("[E]%s(from: %s to: %s)",
                super.toString(), startString, endString);
    }

    /**
     * Checks if the Event object has the specified date.
     *
     * @param toFind The date to find in the Event object.
     * @return True if the Event object has the specified date, otherwise false.
     */
    @Override
    public boolean hasDate(LocalDateTime toFind) {
        return toFind.equals(this.startDate) || toFind.equals(this.endDate);
    }
}
