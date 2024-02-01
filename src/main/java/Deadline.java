import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    @Override
    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[D][X] ";
        } else {
            checkbox = "[D][ ] ";
        }
        return checkbox + name + " (by: " + by + ")";
    }
}
