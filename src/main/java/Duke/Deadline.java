package Duke;

import java.time.LocalDate;

public class Deadline extends SpecialTask {
    String label = "[D]";
//    private final LocalDate date;

    /**
     * Constructs Deadline Object with specified String and LocalDate
     *
     * @param msg
     * @param date
     */
    public Deadline(String msg, LocalDate date) {
        super(msg, date);
//        this.date = date;
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (task instanceof Deadline) {
            Deadline dTask = (Deadline) task;
            return compareMsg(task) && compareDates(dTask);
        }
        return false;
    }

    @Override
    public boolean compareDates(SpecialTask task) {
        System.out.println(this.date.equals(task.date));
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
