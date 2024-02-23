package Duke;

import java.time.LocalDate;

public class Deadline extends SpecialTask {
    String label = "[D]";

    /**
     * Constructs Deadline Object with specified String and LocalDate
     *
     * @param msg
     * @param date
     */
    public Deadline(String msg, LocalDate date) {
        super(msg, date);
    }

    /**
     * Check if input task is a duplicate of this task
     *
     * @param task
     * @return boolean value
     */
    @Override
    public boolean isDuplicate(Task task) {
        if (task instanceof Deadline) {
            Deadline dTask = (Deadline) task;
            return compareMsg(task) && compareDates(dTask);
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
        return this.date.equals(task.date);
    }

    /**
     * Checks if deadline task is due within the date range specified
     *
     * @param fromDate
     * @param toDate
     * @return boolean
     */
    @Override
    public boolean inRange(LocalDate fromDate, LocalDate toDate) {
        return (date.isBefore(toDate) && date.isAfter(fromDate))
                || date.equals(fromDate) || date.equals(toDate);
    }

    @Override
    public String toString() {
        String s = label + super.toString() + " | By: " + super.formatDate(date);
        return s;
    }
}
