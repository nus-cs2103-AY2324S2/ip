package Tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, boolean status, LocalDate deadline) {
        super(description, status, Type.DEADLINE);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        // Deadlines are in format of: yyyy-mm-dd
        return this.deadline;
    }
}
