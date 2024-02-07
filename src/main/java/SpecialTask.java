import java.time.LocalDate;

public abstract class SpecialTask extends Task {
    LocalDate date;

    public SpecialTask(String msg) {
        super(msg);
    }

    public abstract boolean inRange(LocalDate startDate, LocalDate endDate);
}
