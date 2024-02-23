/**
 * Represents a task with a specific start and end time.
 * This class extends the Task class and adds functionality for tasks with event timings.
 */
package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.InvalidEventException;

/**
 * Class for Event Task
 */
public class Event extends Task {
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
        String[] startDateNumbers = start.split("[/ ]");
        int startYear = Integer.parseInt(startDateNumbers[2]);
        int startMonth = Integer.parseInt(startDateNumbers[1]);
        int startDay = Integer.parseInt(startDateNumbers[0]);
        int startHour = Integer.parseInt(startDateNumbers[3].substring(0, 2));
        int startMinutes = Integer.parseInt(startDateNumbers[3].substring(2));
        String[] endDateNumbers = end.split("[/ ]");
        int endYear = Integer.parseInt(endDateNumbers[2]);
        int endMonth = Integer.parseInt(endDateNumbers[1]);
        int endDay = Integer.parseInt(endDateNumbers[0]);
        int endHour = Integer.parseInt(endDateNumbers[3].substring(0, 2));
        int endMinutes = Integer.parseInt(endDateNumbers[3].substring(2));
        try {
            this.startDate = LocalDateTime.of(
                    startYear, startMonth, startDay, startHour, startMinutes);
            this.endDate = LocalDateTime.of(
                    endYear, endMonth, endDay, endHour, endMinutes);
        } catch (DateTimeException e) {
            throw new InvalidEventException();
        }
        if (this.endDate.isBefore(this.startDate)) {
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
        String[] startDateNumbers = start.split("[/ ]");
        int startYear = Integer.parseInt(startDateNumbers[2].trim());
        int startMonth = Integer.parseInt(startDateNumbers[1].trim());
        int startDay = Integer.parseInt(startDateNumbers[0].trim());
        int startHour = Integer.parseInt(startDateNumbers[3].trim().substring(0, 2));
        int startMinutes = Integer.parseInt(startDateNumbers[3].trim().substring(2));
        this.startDate = LocalDateTime.of(
                startYear, startMonth, startDay, startHour, startMinutes);
        String[] endDateNumbers = end.split("[/ ]");
        int endYear = Integer.parseInt(endDateNumbers[2].trim());
        int endMonth = Integer.parseInt(endDateNumbers[1].trim());
        int endDay = Integer.parseInt(endDateNumbers[0].trim());
        int endHour = Integer.parseInt(endDateNumbers[3].trim().substring(0, 2));
        int endMinutes = Integer.parseInt(endDateNumbers[3].trim().substring(2));
        this.endDate = LocalDateTime.of(
                endYear, endMonth, endDay, endHour, endMinutes);
    }

    /**
     * Writes the Event object into a string format for storage.
     *
     * @return A string representing the Event object.
     */
    @Override
    public String writeObject() {
        assert this.startDate != null;
        assert this.endDate != null;
        return String.format("event %s | %s | %s\n",
                super.writeObject(),
                this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
    }

    /**
     * Converts the Event object into a string representation.
     *
     * @return A string representing the Event object.
     */
    @Override
    public String toString() {
        String startString = this.startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        String endString = this.endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
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

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDo) {
            return -1;
        }
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            LocalDateTime compareDate = deadlineTask.getDeadlineDate();
            if (this.endDate.isBefore(compareDate)) {
                return -1;
            }
            if (this.endDate.isAfter(compareDate)) {
                return 1;
            } else {
                return 0;
            }
        }
        if (task instanceof Event) {
            Event eventTask = (Event) task;
            LocalDateTime compareDate = eventTask.getEndDate();
            if (this.endDate.isBefore(compareDate)) {
                return -1;
            }
            if (this.endDate.isAfter(compareDate)) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
