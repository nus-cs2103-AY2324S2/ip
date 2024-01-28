import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime byDate;

    public Deadline(String name, LocalDateTime byDate) {
        super(name);
        this.byDate = byDate;
    }

    public Deadline(String name, LocalDateTime byDate, boolean isDone) {
        super(name, isDone);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.outputDateAsString(byDate) + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String[] getTimes() {
        return new String[] { super.changeDateToString(byDate), "NILL" };
    }
}
