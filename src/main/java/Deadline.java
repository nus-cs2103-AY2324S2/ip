import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {
    private final LocalDateTime deadline;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        try {
            this.deadline = LocalDateTime.parse(deadline, this.inputFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.invalidFormat("Deadline"));
        }
    }
    public Deadline(String description, String deadline, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            this.deadline = LocalDateTime.parse(deadline, this.inputFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.invalidFormat("Deadline"));
        }
    }
    public void isDueBy(String date) {
        if (this.deadline.equals(LocalDateTime.parse(date, this.inputFormat))) {
            System.out.println(this);
        }
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(this.outputFormat));
    }
    @Override
    public String toFileFormat() {
        return String.format("D |&| %s |&| %s", super.toFileFormat(), this.deadline.format(this.inputFormat));
    }
}
