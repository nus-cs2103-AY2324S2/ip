package Duke;

import java.time.LocalDate;

public class Deadline extends SpecialTask {
    String label = "[D]";
    private final LocalDate date;

    public Deadline(String msg, LocalDate date) {
        super(msg);
        this.date = date;
    }

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
