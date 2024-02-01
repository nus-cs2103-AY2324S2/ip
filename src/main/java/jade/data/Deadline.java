package jade.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadlineDate;

    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String description, LocalDate deadlineDate, boolean isDone) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }

    public String dateFormatter(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean isSameDate(LocalDate date) {
        return date.equals(deadlineDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),  dateFormatter(deadlineDate));
    }

    @Override
    public String taskFormatter() {
        return String.format("D | %s | %s | %s\n", statusFormatter(), description,  dateFormatter(deadlineDate));
    }
}
