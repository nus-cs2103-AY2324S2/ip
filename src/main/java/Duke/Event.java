package Duke;

import java.time.LocalDate;

public class Event extends SpecialTask {
    String label = "[E]";
//    LocalDate fromDate;
//    LocalDate toDate;

    /**
     * Constructs Event object with specified String and the start and end LocalDate.
     *
     * @param msg
     * @param fromDate
     * @param toDate
     */
    public Event(String msg, LocalDate fromDate, LocalDate toDate) {
        super(msg, fromDate, toDate);
//        this.fromDate = fromDate;
//        this.toDate = toDate;
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (task instanceof Event) {
            Event eTask = (Event) task;
            return compareMsg(task) && compareDates(eTask);
        }
        return false;
    }

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
