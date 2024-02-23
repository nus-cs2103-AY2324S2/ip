package Duke;

import java.time.LocalDate;

public class Event extends SpecialTask {
    String label = "[E]";

    /**
     * Constructs Event object with specified String and the start and end LocalDate.
     *
     * @param msg
     * @param fromDate
     * @param toDate
     */
    public Event(String msg, LocalDate fromDate, LocalDate toDate) {
        super(msg, fromDate, toDate);
    }

    /**
     * Check if input task is a duplicate of this task
     *
     * @param task
     * @return boolean value
     */
    @Override
    public boolean isDuplicate(Task task) {
        if (task instanceof Event) {
            Event eTask = (Event) task;
            return compareMsg(task) && compareDates(eTask);
        }
        return false;
    }

    /**
     * Check if dates of the two task are same
     *
     * @param task
     * @return boolean
     */
    @Override
    public boolean compareDates(SpecialTask task) {
        return this.fromDate.equals(task.fromDate) && this.toDate.equals(task.toDate);
    }


    /**
     * Checks if event occurs within date range specified.
     *
     * @param startDate
     * @param endDate
     * @return boolean
     */
    @Override
    public boolean inRange(LocalDate startDate, LocalDate endDate) {
        return (startDate.isBefore(toDate) && endDate.isAfter(fromDate))
                || startDate.equals(toDate) || endDate.equals(fromDate);
    }

    @Override
    public String toString() {
        return label + super.toString() + " | From: " +
                    super.formatDate(fromDate) + " To: " +super.formatDate(toDate);
    }
}
