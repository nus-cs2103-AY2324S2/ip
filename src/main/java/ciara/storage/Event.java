package ciara.storage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The Event class defines a 'Event' task used for the application
 *
 * @author Ryan NgWH
 */
public class Event extends Task {
    /**
     * Start date/time of the Event task
     */
    private Instant startDate;

    /**
     * End date/time of the Event task
     */
    private Instant endDate;

    /**
     * Create an Event task
     *
     * @param description Description of the event
     * @param startDate   Start date of the event
     * @param endDate     End date of the event
     */
    public Event(String description, Instant startDate, Instant endDate) {
        super(description, TaskType.EVENT, false, false);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Create an Event task
     *
     * @param description Description of the event
     * @param startDate   Start date of the event
     * @param endDate     End date of the event
     * @param isCompleted Status of the event
     * @param isArchived  Visibility of the event
     */
    public Event(String description, Instant startDate, Instant endDate, boolean isCompleted, boolean isArchived) {
        super(description, TaskType.EVENT, isCompleted, isArchived);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Create an Event task
     *
     * @param description Description of the event
     * @param startDate   Start date of the event (in epoch milliseconds)
     * @param endDate     End date of the event (in epoch milliseconds)
     * @param isCompleted Status of the event
     * @param isArchived  Visibility of the event
     */
    public Event(String description, long startDate, long endDate, boolean isCompleted, boolean isArchived) {
        super(description, TaskType.EVENT, isCompleted, isArchived);
        this.startDate = Instant.ofEpochMilli(startDate);
        this.endDate = Instant.ofEpochMilli(endDate);
    }

    /**
     * Get the start date of the event
     *
     * @return Start date of the event
     */
    public long getStartDate() {
        return this.startDate.toEpochMilli();
    }

    /**
     * Get the end date of the event
     *
     * @return End date of the event
     */
    public long getEndDate() {
        return this.endDate.toEpochMilli();
    }

    /**
     * Check if event encompasses the specified date
     *
     * @param date Date to check against
     *
     * @return True if event is encompasses specified date, false otherwise
     */
    public boolean encompasses(Instant date) {
        // Get dates in local time zone
        LocalDate startDateLocal = this.startDate.atZone(ZoneId.of("+8")).toLocalDate();
        LocalDate endDateLocal = this.endDate.atZone(ZoneId.of("+8")).toLocalDate();
        LocalDate dateLocal = date.atZone(ZoneId.of("+8")).toLocalDate();

        boolean isBetweenDates = dateLocal.isAfter(startDateLocal) && dateLocal.isBefore(endDateLocal);
        boolean isOnStartDate = dateLocal.equals(startDateLocal);
        boolean isOnEndDate = dateLocal.equals(endDateLocal);

        return isBetweenDates || isOnStartDate || isOnEndDate;
    }

    /**
     * String representation of an event
     *
     * @return String representation of the event
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mma")
                .withZone(ZoneId.of("+8"));

        return String.format("[E]%s (from: %s to: %s)", super.toString(), formatter.format(startDate),
                formatter.format(endDate));
    }

    /**
     * Indicates whether some other object is "equal to" this Event
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Event) {
            Event task = (Event) obj;

            return super.equals(task)
                    && this.startDate.equals(task.startDate)
                    && this.endDate.equals(task.endDate);
        }

        return false;
    }
}
