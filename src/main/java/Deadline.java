import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private LocalDate endDate;

    public Deadline(String name, LocalDate endDate) {
        super(name);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String by = this.endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return String.format("[D]%s %s (by: %s)", (super.status ? "[X]" : "[ ]"), super.name, by);
    }
}
