package Duke;

import java.time.LocalDate;

public abstract class SpecialTask extends Task {
    LocalDate date;
    LocalDate fromDate;
    LocalDate toDate;

    public SpecialTask(String msg, LocalDate date) {
        super(msg);
        this.date = date;
    }

    public SpecialTask(String msg, LocalDate fromDate, LocalDate toDate) {
        super(msg);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public abstract boolean inRange(LocalDate startDate, LocalDate endDate);

    public abstract boolean compareDates(SpecialTask task);
}
