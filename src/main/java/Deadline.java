import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private final LocalDate deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE);
    }
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE);
    }
    public void isDueBy(String date) {
        if (this.deadline.equals(LocalDate.parse(date))) {
            System.out.println(this);
        }
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
    @Override
    public String toFileFormat() {
        return String.format("D %s %s", super.toFileFormat(), this.deadline);
    }
}
