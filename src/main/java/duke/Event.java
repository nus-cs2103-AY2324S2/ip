package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a starting time and ending time
 */
public class Event extends Task implements TaskWithTime{
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs a new Event.
     *
     * @param name      The content of the task
     * @param startTime The starting time of the task
     * @param endTime   The endting time of the task
     * @throws DukeException if the time parsing is unsuccessful or the event ends before it starts
     */
    public Event(String name, String startTime, String endTime) throws DukeException {
        super(name);
        this.startTime = Task.parse(startTime);
        this.endTime = Task.parse(endTime);
        try {
            assert this.startTime.isBefore(this.endTime);
        } catch (AssertionError e) {
            throw new DukeException("Oops! It seems the event ends before it starts!");
        }
    }

    /**
     * Returns a timestamp to compare the order of events.
     *
     * @return a LocalDateTime object to represent the timestamp of the task
     */
    public LocalDateTime getTimestamp() {
        return startTime;
    }

    /**
     * {@inheritDoc}
     * Specifies the type of the task.
     */
    @Override
    String taskToLine() {
        return "E | " + super.taskToLine() + " | " + startTime.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")) + " | " + endTime.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Checks if the searching date is between the starting time and ending time.
     *
     * @param localDate The given date that the user wishes to check
     * @return True if the searching date is between the starting time and ending time
     */
    @Override
    boolean canMatchDate(LocalDate localDate) {
        return startTime.toLocalDate().equals(localDate) ||
                endTime.toLocalDate().equals(localDate) ||
                (startTime.toLocalDate().isBefore(localDate) &&
                        endTime.toLocalDate().isAfter(localDate));
    }

    /**
     * {@inheritDoc}
     * Specifies the type of the task, the starting time and ending time.
     * If the event is happening on the same day, the output string is simplified.
     */
    @Override
    public String toString() {
        if (startTime.toLocalDate().equals(endTime.toLocalDate())) {
            return "[E]" + super.toString() + String.format(" (from: %s to: %s) ", startTime.format(DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm")), endTime.toLocalTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            return "[E]" + super.toString() + String.format(" (from: %s to: %s) ", startTime.format(DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm")), endTime.format(DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm")));
        }
    }
}
