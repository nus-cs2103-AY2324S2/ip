package Duke;

import java.time.LocalDate;

public class Event extends SpecialTask {
    String label = "[E]";
    LocalDate fromDate;
    LocalDate toDate;
    boolean hasDate;

    public Event(String msg, LocalDate fromDate, LocalDate toDate) {
        super(msg);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.hasDate = true;
    }
    

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
