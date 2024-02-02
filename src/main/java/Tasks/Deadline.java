package Tasks;

import Tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String description, LocalDate date) {
        super(description);
        this.deadline = date;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", this.description, this.deadline);
    }
    public String getCommand() {
        System.out.println(this.deadline);
        return String.format("deadline %s /by %s\n%b\n", this.description, this.deadline, this.isDone);
    }
}
